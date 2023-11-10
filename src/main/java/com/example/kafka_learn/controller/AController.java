package com.example.kafka_learn.controller;


import com.example.kafka_learn.queue.TopicPartitionInfo;
import com.example.kafka_learn.queue.msg.DefaultQueueMsg;
import com.example.kafka_learn.queue.provider.QueueProducerProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("A")
@RequiredArgsConstructor
@Slf4j
public class AController {
    private final QueueProducerProvider queueProducerProvider;

    private Integer result = 0;

    @RequestMapping(value = "/sum", method = RequestMethod.GET)
    @ResponseBody
    public void sum(@PathVariable("num") String number) {
        queueProducerProvider.getAMsgProducer().send(new TopicPartitionInfo("a",0),new DefaultQueueMsg(number),null);
    }

    @RequestMapping(value = "/result", method = RequestMethod.GET)
    @ResponseBody
    public Integer getAssetById() {
        return result;
    }

    public void sum() {

    }
}
