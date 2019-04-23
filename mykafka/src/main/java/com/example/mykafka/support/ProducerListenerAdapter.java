package com.example.mykafka.support;
import org.apache.kafka.clients.producer.RecordMetadata;

/**
 * No-op implementation of {@link ProducerListener}, to be used as base class for other implementations.
 *
 * @param <K> the key type.
 * @param <V> the value type.
 *
 * @author Marius Bogoevici
 * @author Gary Russell
 * @author Artem Bilan
 */
public abstract class ProducerListenerAdapter<K, V> implements ProducerListener<K, V> {

    @Override
    public void onSuccess(String topic, Integer partition, K key, V value, RecordMetadata recordMetadata) {
    }

    @Override
    public void onError(String topic, Integer partition, K key, V value, Exception exception) {
    }

    @Override
    public boolean isInterestedInSuccess() {
        return false;
    }

}