package com.example.mykafka.core;

import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.Metric;
import org.apache.kafka.common.MetricName;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.errors.ProducerFencedException;
import org.apache.kafka.common.serialization.Serializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.Lifecycle;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

public class DefaultKafkaProducerFactory<K, V> implements ProducerFactory<K, V>, Lifecycle, DisposableBean {
    private static final int DEFAULT_PHYSICAL_CLOSE_TIMEOUT = 30;
    private static final Logger logger = LoggerFactory.getLogger(DefaultKafkaProducerFactory.class);
    private volatile CloseSafeProducer<K, V> producer;
    private final Map<String, Object> configs;
    private final BlockingQueue<CloseSafeProducer<K, V>> cache = new LinkedBlockingDeque<>();
    private int physicalCloseTimeout = DEFAULT_PHYSICAL_CLOSE_TIMEOUT;
    private Serializer<K> keySerializer;
    private Serializer<V> valueSerializer;
    private volatile boolean running;

    public DefaultKafkaProducerFactory(Map<String, Object> configs) {
        this.configs = configs;
    }

    public DefaultKafkaProducerFactory(Map<String, Object> configs, Serializer<K> keySerializer,
                                       Serializer<V> valueSerializer) {
        this.configs = new HashMap<>(configs);
        this.keySerializer = keySerializer;
        this.valueSerializer = valueSerializer;
    }

    @Override
    public void start() {
        this.running = true;
    }

    @Override
    public void stop() {
        try {
            destroy();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean isRunning() {
        return this.running;
    }

    @Override
    public void destroy() throws Exception {
        CloseSafeProducer<K, V> producer = this.producer;
        this.producer = null;
        if (producer != null) {
            producer.delegate.close(this.physicalCloseTimeout, TimeUnit.SECONDS);
        }
        producer = this.cache.poll();
        while (producer != null) {
            producer.delegate.close();
            producer = this.cache.poll();
        }
    }

    @Override
    public Producer<K, V> createProducer() {
        if (this.producer == null) {
            synchronized (this) {
                if (this.producer == null) {
                    this.producer = new CloseSafeProducer<>(createKafkaProducer());
                }
            }
        }
        return this.producer;
    }

    protected Producer<K, V> createKafkaProducer() {
        return new KafkaProducer<K, V>(this.configs, this.keySerializer, this.valueSerializer);
    }


    private static class CloseSafeProducer<K, V> implements Producer<K, V> {
        private final Producer<K, V> delegate;

        public CloseSafeProducer(Producer<K, V> delegate) {
            this.delegate = delegate;
        }

        @Override
        public void initTransactions() {

        }

        @Override
        public void beginTransaction() throws ProducerFencedException {

        }

        @Override
        public void sendOffsetsToTransaction(Map<TopicPartition, OffsetAndMetadata> offsets, String consumerGroupId) throws ProducerFencedException {

        }

        @Override
        public void commitTransaction() throws ProducerFencedException {

        }

        @Override
        public void abortTransaction() throws ProducerFencedException {

        }

        @Override
        public Future<RecordMetadata> send(ProducerRecord<K, V> record) {
            return null;
        }

        @Override
        public Future<RecordMetadata> send(ProducerRecord<K, V> record, Callback callback) {
            return null;
        }

        @Override
        public void flush() {

        }

        @Override
        public List<PartitionInfo> partitionsFor(String topic) {
            return null;
        }

        @Override
        public Map<MetricName, ? extends Metric> metrics() {
            return null;
        }

        @Override
        public void close() {

        }

        @Override
        public void close(Duration timeout) {

        }
    }
}
