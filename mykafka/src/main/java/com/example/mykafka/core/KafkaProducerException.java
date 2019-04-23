package com.example.mykafka.core;

import org.apache.kafka.clients.producer.ProducerRecord;

public class KafkaProducerException extends Exception{
    private final ProducerRecord<?, ?> producerRecord;
    private final String message;

    public KafkaProducerException(ProducerRecord<?, ?> producerRecord, String message, Exception cause) {
        this.producerRecord = producerRecord;
        this.message = message;
    }

    public ProducerRecord<?, ?> getProducerRecord() {
        return producerRecord;
    }
}
