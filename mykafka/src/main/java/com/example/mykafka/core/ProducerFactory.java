package com.example.mykafka.core;

import org.apache.kafka.clients.producer.Producer;

public interface ProducerFactory<K, V> {
    Producer<K, V> createProducer();

}
