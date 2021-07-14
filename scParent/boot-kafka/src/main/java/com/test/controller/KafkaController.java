package com.test.controller;


import com.test.kafka.KafkaClientConsumer;
import com.test.kafka.KafkaClientProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：Breeze
 * @date ：Created in 2020/6/7 21:18
 * @description：
 */

@RestController
public class KafkaController {

    @Autowired
    KafkaClientProducer producer;

    @Autowired
    KafkaClientConsumer consumer;

    @GetMapping("/kafkaClient")
    public String kafkaClient(){
        producer.sendMsg();
        return "ok";
    }

    @GetMapping("/sub")
    public String kafkaSub(){
        consumer.record();
        return "ok";
    }

}
