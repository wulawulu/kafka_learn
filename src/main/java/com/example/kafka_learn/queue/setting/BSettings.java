package com.example.kafka_learn.queue.setting;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Lazy
@Data
@Component
public class BSettings {
    @Value("${queue.b.topic:a}")
    private String topic;
}
