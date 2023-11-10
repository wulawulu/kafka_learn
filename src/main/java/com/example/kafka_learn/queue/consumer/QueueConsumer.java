package com.example.kafka_learn.queue.consumer;

import com.example.kafka_learn.queue.TopicPartitionInfo;
import com.example.kafka_learn.queue.msg.QueueMsg;
import org.apache.kafka.common.protocol.types.Field;

import java.util.List;
import java.util.Set;

public interface QueueConsumer <T extends QueueMsg>{
    String getTopic();

    void subscribe();

    void subscribe(Set<TopicPartitionInfo> partitions);

    void unsubscribe();

    List<T> poll(long durationInMillis);

    void commit();

    boolean isStopped();

    void onQueueDelete();

    boolean isQueueDeleted();

    List<String> getFullTopicNames();
}
