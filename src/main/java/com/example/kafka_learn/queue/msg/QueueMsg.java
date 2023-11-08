package com.example.kafka_learn.queue.msg;

import java.util.UUID;

public interface QueueMsg {

    UUID getKey();

    QueueMsgHeaders getHeaders();

    byte[] getData();
}
