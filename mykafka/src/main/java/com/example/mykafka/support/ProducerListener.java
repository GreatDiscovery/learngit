package com.example.mykafka.support;

import org.apache.kafka.clients.producer.RecordMetadata;

public interface ProducerListener<K, V> {
    /**
     * Invoked after the successful send of a message (that is, after it has been acknowledged by the broker).
     *
     * @param topic          the destination topic
     * @param partition      the destination partition (could be null)
     * @param key            the key of the outbound message
     * @param value          the payload of the outbound message
     * @param recordMetadata the result of the successful send operation
     */
    void onSuccess(String topic, Integer partition, K key, V value, RecordMetadata recordMetadata);

    /**
     * Invoked after an attempt to send a message has failed.
     *
     * @param topic     the destination topic
     * @param partition the destination partition (could be null)
     * @param key       the key of the outbound message
     * @param value     the payload of the outbound message
     * @param exception the exception thrown
     */
    void onError(String topic, Integer partition, K key, V value, Exception exception);

    /**
     * Return true if this listener is interested in success as well as failure.
     *
     * @return true to express interest in successful sends.
     */
    boolean isInterestedInSuccess();

}
