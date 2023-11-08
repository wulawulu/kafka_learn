package com.example.kafka_learn.queue;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TopicPartitionInfo {
    private final String topic;
    private final Integer partition;
}
