package com.example.kafka_learn.queue.memory;

import com.example.kafka_learn.queue.msg.QueueMsg;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

@Component
@Slf4j
public class DefaultInMemoryStorage implements InMemoryStorage {

    private final ConcurrentHashMap<String, BlockingQueue<QueueMsg>> storage = new ConcurrentHashMap<>();

    @Override
    public void printStats() {
        if (log.isDebugEnabled()) {
            storage.forEach((topic, queue) -> {
                if (!queue.isEmpty()) {
                    log.debug("[{}] Queue Size [{}]", topic, queue.size());
                }
            });
        }
    }

    @Override
    public int getLagTotal() {
        return storage.values().stream().map(BlockingQueue::size).reduce(0, Integer::sum);
    }

    @Override
    public boolean put(String topic, QueueMsg msg) {
        return storage.computeIfAbsent(topic, (t) -> new LinkedBlockingQueue<>()).add(msg);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends QueueMsg> List<T> get(String topic) throws InterruptedException {
        final BlockingQueue<QueueMsg> queue = storage.get(topic);
        if (queue != null) {
            final QueueMsg firstMsg = queue.poll();
            if (firstMsg != null) {
                final int queueSize = queue.size();
                if (queueSize > 0) {
                    final List<QueueMsg> entities = new ArrayList<>(Math.min(queueSize, 999) + 1);
                    entities.add(firstMsg);
                    queue.drainTo(entities, 999);
                    return (List<T>) entities;
                }
                return Collections.singletonList((T) firstMsg);
            }
        }
        return Collections.emptyList();
    }
}
