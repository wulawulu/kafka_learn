package com.example.kafka_learn.queue.msg;

import lombok.Data;

import java.util.UUID;

@Data
public class DefaultQueueMsg implements QueueMsg {
    private final UUID key;
    private final byte[] data;
    private final QueueMsgHeaders headers;

    public DefaultQueueMsg(QueueMsg msg) {
        this.key = msg.getKey();
        this.data = msg.getData();
        QueueMsgHeaders headers = new QueueMsgHeaders();
        msg.getHeaders().getData().forEach(headers::put);
        this.headers = headers;
    }

}