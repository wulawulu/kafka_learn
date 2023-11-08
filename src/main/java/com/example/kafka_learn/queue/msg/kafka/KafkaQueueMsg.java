package com.example.kafka_learn.queue.msg.kafka;

import com.example.kafka_learn.queue.msg.QueueMsg;
import com.example.kafka_learn.queue.msg.QueueMsgHeaders;
import org.apache.kafka.clients.consumer.ConsumerRecord;

import java.util.UUID;

public class KafkaQueueMsg implements QueueMsg {
    private final UUID key;
    private final QueueMsgHeaders headers;
    private final byte[] data;

    public KafkaQueueMsg(ConsumerRecord<String, byte[]> record) {
        this.key = UUID.fromString(record.key());
        QueueMsgHeaders headers = new QueueMsgHeaders();
        record.headers().forEach(header -> {
            headers.put(header.key(), header.value());
        });
        this.headers = headers;
        this.data = record.value();
    }

    @Override
    public UUID getKey() {
        return key;
    }

    @Override
    public QueueMsgHeaders getHeaders() {
        return headers;
    }

    @Override
    public byte[] getData() {
        return data;
    }
}
