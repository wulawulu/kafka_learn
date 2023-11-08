package com.example.kafka_learn.queue.msg.kafka;

import com.example.kafka_learn.queue.msg.QueueMsgMetadata;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.kafka.clients.producer.RecordMetadata;

@Data
@AllArgsConstructor
public class KafkaQueueMsgMetadata implements QueueMsgMetadata {
    private RecordMetadata metadata;
}
