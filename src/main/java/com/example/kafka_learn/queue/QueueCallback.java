package com.example.kafka_learn.queue;

import com.example.kafka_learn.queue.msg.QueueMsgMetadata;

public interface QueueCallback {
    void onSuccess(QueueMsgMetadata metadata);

    void onFailure(Throwable t);
}
