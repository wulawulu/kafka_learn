package com.example.kafka_learn.queue.provider;

import com.example.kafka_learn.queue.msg.QueueMsg;
import com.example.kafka_learn.queue.producer.QueueProducer;

public interface AQueueFactory {
    QueueProducer<QueueMsg> createAMsgProducer();
    QueueProducer<QueueMsg> createBMsgProducer();
}
