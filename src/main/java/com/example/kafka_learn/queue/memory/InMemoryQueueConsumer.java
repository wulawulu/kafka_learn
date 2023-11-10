package com.example.kafka_learn.queue.memory;

import com.example.kafka_learn.queue.TopicPartitionInfo;
import com.example.kafka_learn.queue.consumer.QueueConsumer;
import com.example.kafka_learn.queue.msg.QueueMsg;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
public class InMemoryQueueConsumer<T extends QueueMsg> implements QueueConsumer<T> {
    private final InMemoryStorage storage;

    private volatile Set<TopicPartitionInfo> partitions;

    private volatile boolean stopped;

    private volatile boolean subscribed;

    private volatile boolean queueDeleted;

    private final String topic;

    public InMemoryQueueConsumer(InMemoryStorage storage, String topic) {
        this.storage = storage;
        this.topic = topic;
        stopped = false;
    }

    @Override
    public String getTopic() {
        return topic;
    }

    @Override
    public void subscribe() {
        partitions = Collections.singleton(new TopicPartitionInfo(topic, null));
        subscribed = true;
    }

    @Override
    public void subscribe(Set<TopicPartitionInfo> partitions) {
        this.partitions = partitions;
        subscribed = true;
    }

    @Override
    public void unsubscribe() {
        stopped = true;
    }

    @Override
    public List<T> poll(long durationInMillis) {
        if (subscribed) {
            List<T> messages = partitions
                    .stream()
                    .map(tpi -> {
                        try {
                            return storage.get(tpi.getTopic());
                        } catch (InterruptedException e) {
                            if (!stopped) {
                                log.error("Queue was interrupted.", e);
                            }
                            return Collections.emptyList();
                        }
                    })
                    .flatMap(List::stream)
                    .map(msg -> (T) msg).collect(Collectors.toList());
            if (!messages.isEmpty()) {
                return messages;
            }
            try {
                Thread.sleep(durationInMillis);
            } catch (InterruptedException e) {
                if (!stopped) {
                    log.error("Failed to sleep.", e);
                }
            }
        }
        return Collections.emptyList();
    }

    @Override
    public void commit() {

    }

    @Override
    public boolean isStopped() {
        return stopped;
    }

    @Override
    public void onQueueDelete() {
        queueDeleted = true;
    }

    @Override
    public boolean isQueueDeleted() {
        return queueDeleted;
    }

    @Override
    public List<String> getFullTopicNames() {
        return partitions.stream().map(TopicPartitionInfo::getTopic).collect(Collectors.toList());
    }
}
