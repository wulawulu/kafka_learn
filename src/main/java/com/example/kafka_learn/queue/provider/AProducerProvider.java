package com.example.kafka_learn.queue.provider;

import com.example.kafka_learn.queue.msg.QueueMsg;
import com.example.kafka_learn.queue.producer.QueueProducer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnExpression("'${service.type:null}'=='monolith'")
public class AProducerProvider implements QueueProducerProvider {
    @Override
    public QueueProducer<QueueMsg> getAMsgProducer() {
        return null;
    }

    @Override
    public QueueProducer<QueueMsg> getBMsgProducer() {
        return null;
    }

    @Override
    public QueueProducer<QueueMsg> getCMsgProducer() {
        return null;
    }
}
