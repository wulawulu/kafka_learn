package com.example.kafka_learn.queue.producer;

import com.example.kafka_learn.queue.QueueCallback;
import com.example.kafka_learn.queue.msg.QueueMsg;
import com.example.kafka_learn.queue.TopicPartitionInfo;

public interface QueueProducer<T extends QueueMsg> {
    void init();

    String getDefaultTopic();

    void send(TopicPartitionInfo tpi, T msg, QueueCallback callback);

    void stop();
}
