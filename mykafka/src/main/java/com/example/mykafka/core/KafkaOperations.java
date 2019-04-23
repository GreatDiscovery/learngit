package com.example.mykafka.core;

import com.example.mykafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;

public interface KafkaOperations<K, V> {
    ListenableFuture<SendResult<K, V>> send(String topic, V data);
    void flush();
}
