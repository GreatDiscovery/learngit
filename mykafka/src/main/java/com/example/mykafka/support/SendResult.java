package com.example.mykafka.support;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

public class SendResult<K, V> {
    private final ProducerRecord<K, V> producerRecord;
    private final RecordMetadata recordMetadata;

    public SendResult(ProducerRecord<K, V> producerRecord, RecordMetadata recordMetadata) {
        this.producerRecord = producerRecord;
        this.recordMetadata = recordMetadata;
    }

    public ProducerRecord<K, V> getProducerRecord() {
        return producerRecord;
    }

    public RecordMetadata getRecordMetadata() {
        return recordMetadata;
    }
}
