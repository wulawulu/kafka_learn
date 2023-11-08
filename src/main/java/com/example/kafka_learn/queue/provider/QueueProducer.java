package com.example.kafka_learn.queue.provider;

import com.example.kafka_learn.queue.QueueCallback;
import com.example.kafka_learn.queue.msg.QueueMsg;
import org.apache.kafka.common.TopicPartitionInfo;

public interface QueueProducer<T extends QueueMsg> {
    void init();

    String getDefaultTopic();

    void send(TopicPartitionInfo tpi, T msg, QueueCallback callback);

    void stop();
}
