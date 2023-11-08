package com.example.kafka_learn.queue.provider;

import com.example.kafka_learn.queue.msg.QueueMsg;
import com.example.kafka_learn.queue.producer.QueueProducer;

public interface QueueProducerProvider {
    QueueProducer<QueueMsg> getAMsgProducer();
    QueueProducer<QueueMsg> getBMsgProducer();
    QueueProducer<QueueMsg> getCMsgProducer();
}
