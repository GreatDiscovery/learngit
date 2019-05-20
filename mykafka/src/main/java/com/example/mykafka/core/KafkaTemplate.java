package com.example.mykafka.core;

import com.example.mykafka.support.LoggingProducerListener;
import com.example.mykafka.support.ProducerListener;
import com.example.mykafka.support.SendResult;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.SettableListenableFuture;

public class KafkaTemplate<K, V> implements KafkaOperations {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final ProducerFactory<K, V> producerFactory;
    private final boolean autoFlush;
    private final ThreadLocal<Producer<K, V>> producers = new ThreadLocal<>();
    private volatile String defaultTopic;
    private volatile ProducerListener<K, V> producerListener = new LoggingProducerListener<K, V>();

    public KafkaTemplate(ProducerFactory<K, V> producerFactory) {
       this(producerFactory, false);
    }

    public KafkaTemplate(ProducerFactory<K, V> producerFactory, boolean autoFlush) {
        this.producerFactory = producerFactory;
        this.autoFlush = autoFlush;
    }

    public String getDefaultTopic() {
        return defaultTopic;
    }

    public void setDefaultTopic(String defaultTopic) {
        this.defaultTopic = defaultTopic;
    }

    @Override
    public ListenableFuture<SendResult<K, V>>  send(String topic, Object data) {
        ProducerRecord<K, V> producerRecord = new ProducerRecord(topic, data);
        return doSend(producerRecord);
    }

    protected ListenableFuture<SendResult<K, V>> doSend(final ProducerRecord<K, V> producerRecord) {
        final Producer<K, V> producer = getTheProducer();
        if (this.logger.isTraceEnabled()) {
            this.logger.trace("Sending: " + producerRecord);
        }
        final SettableListenableFuture<SendResult<K, V>> future = new SettableListenableFuture<>();
        producer.send(producerRecord, new Callback() {
            @Override
            public void onCompletion(RecordMetadata metadata, Exception exception) {
                try {
                    if (exception == null) {
                        future.set(new SendResult<>(producerRecord, metadata));
                        if (KafkaTemplate.this.producerListener != null
                                && KafkaTemplate.this.producerListener.isInterestedInSuccess()) {
                            KafkaTemplate.this.producerListener.onSuccess(producerRecord.topic(), producerRecord.partition(),
                                    producerRecord.key(), producerRecord.value(), metadata);
                        }
                        if (KafkaTemplate.this.logger.isTraceEnabled()) {
                            KafkaTemplate.this.logger.trace("Send ok:" + producerRecord + ", metadate:" + metadata);
                        }
                    } else {
                        future.setException(new KafkaProducerException(producerRecord, "Failed to send", exception));
                        if (KafkaTemplate.this.producerListener != null) {
                            KafkaTemplate.this.producerListener.onError(producerRecord.topic(),
                                    producerRecord.partition(),
                                    producerRecord.key(),
                                    producerRecord.value(),
                                    exception);
                        }
                        if (KafkaTemplate.this.logger.isDebugEnabled()) {
                            KafkaTemplate.this.logger.debug("Failed to send: " + producerRecord, exception);
                        }
                    }
                } finally {
                    if (KafkaTemplate.this.producers.get() == null) {
                        closeProducer(producer);
                    }
                }
            }
        });
        if (this.autoFlush) {
            flush();
        }
        if (this.logger.isTraceEnabled()) {
            this.logger.trace("Sent: " + producerRecord);
        }
        return future;
    }

    private Producer<K, V> getTheProducer() {
        return this.producerFactory.createProducer();
    }

    @Override
    public void flush() {
       Producer<K, V> producer = getTheProducer();
       try {
           producer.flush();
       }
       finally {
           closeProducer(producer);
       }
    }
    protected void closeProducer(Producer<K, V> producer) {
        producer.close();
    }
}
