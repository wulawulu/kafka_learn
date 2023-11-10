package com.example.kafka_learn.queue.memory;

import com.example.kafka_learn.queue.msg.QueueMsg;

import java.util.List;

public interface InMemoryStorage {
    void printStats();

    int getLagTotal();

    boolean put(String topic, QueueMsg msg);

    <T extends QueueMsg> List<T> get(String topic) throws InterruptedException;
}
