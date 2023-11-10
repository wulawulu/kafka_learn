package com.example.kafka_learn.queue.memory;

import com.example.kafka_learn.queue.QueueCallback;
import com.example.kafka_learn.queue.msg.QueueMsg;
import com.example.kafka_learn.queue.producer.QueueProducer;
import lombok.Data;
import com.example.kafka_learn.queue.TopicPartitionInfo;

@Data
public class InMemoryQueueProducer<T extends QueueMsg>implements QueueProducer<T> {
    private final InMemoryStorage storage;
    private final String defaultTopic;


    public InMemoryQueueProducer(InMemoryStorage storage, String defaultTopic) {
        this.storage = storage;
        this.defaultTopic = defaultTopic;
    }

    @Override
    public void init() {

    }

    @Override
    public void send(TopicPartitionInfo tpi, T msg, QueueCallback callback) {
        boolean result = storage.put(tpi.getTopic(), msg);
        if (result) {
            if (callback != null) {
                callback.onSuccess(null);
            }
        } else {
            if (callback!=null) {
                callback.onFailure(new RuntimeException("Failed add msg to InMemoryQueue"));
            }
        }
    }

    @Override
    public void stop() {

    }
}
