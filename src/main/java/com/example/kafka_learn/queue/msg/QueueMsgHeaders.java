package com.example.kafka_learn.queue.msg;

import java.util.HashMap;
import java.util.Map;

public class QueueMsgHeaders {
    protected final Map<String, byte[]> data = new HashMap<>();

    public byte[] put(String key, byte[] value) {
        return data.put(key, value);
    }

    public byte[] get(String key) {
        return data.get(key);
    }

    public Map<String, byte[]> getData() {
        return data;
    }
}
