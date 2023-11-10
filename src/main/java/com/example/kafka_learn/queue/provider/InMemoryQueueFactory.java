package com.example.kafka_learn.queue.provider;

import com.example.kafka_learn.queue.memory.InMemoryStorage;
import com.example.kafka_learn.queue.memory.InMemoryQueueProducer;
import com.example.kafka_learn.queue.msg.QueueMsg;
import com.example.kafka_learn.queue.producer.QueueProducer;
import com.example.kafka_learn.queue.setting.ASettings;
import com.example.kafka_learn.queue.setting.BSettings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@ConditionalOnExpression("'${queue.type:null}'=='in-memory' && '${service.type:null}'=='monolith'")
public class InMemoryQueueFactory implements AQueueFactory,BQueueFactory{


    private final InMemoryStorage storage;

    private final ASettings aSettings;
    private final BSettings bSettings;

    public InMemoryQueueFactory(InMemoryStorage storage, ASettings aTopic, BSettings bSettings) {
        this.storage = storage;
        this.aSettings = aTopic;
        this.bSettings = bSettings;
    }

    @Override
    public QueueProducer<QueueMsg> createAMsgProducer() {
        return new InMemoryQueueProducer<>(storage, aSettings.getTopic());
    }

    @Override
    public QueueProducer<QueueMsg> createBMsgProducer() {
        return new InMemoryQueueProducer<>(storage, bSettings.getTopic());
    }

}
