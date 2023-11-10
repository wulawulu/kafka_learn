package com.example.kafka_learn.queue.provider;

import com.example.kafka_learn.queue.msg.QueueMsg;
import com.example.kafka_learn.queue.producer.QueueProducer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@ConditionalOnExpression("'${service.type:null}'=='monolith'")
public class AProducerProvider implements QueueProducerProvider {

    private final AQueueFactory aQueueProvider;

    private QueueProducer<QueueMsg> a;
    private QueueProducer<QueueMsg> b;

    @PostConstruct
    public void init() {
        a = aQueueProvider.createAMsgProducer();
        b = aQueueProvider.createBMsgProducer();
    }

    public AProducerProvider(AQueueFactory aQueueProvider) {
        this.aQueueProvider = aQueueProvider;
    }

    @Override
    public QueueProducer<QueueMsg> getAMsgProducer() {
        return a;
    }

    @Override
    public QueueProducer<QueueMsg> getBMsgProducer() {
        return b;
    }

}
