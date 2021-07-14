package com.test.kafka;

import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.stereotype.Service;

import java.util.Properties;

/**
 * @author ：Breeze
 * @date ：Created in 2021/5/12 23:51
 * @description：
 */

@Service
public class KafkaClientProducer {

    public void sendMsg(){

        Properties properties = new Properties();


        properties.put("bootstrap.servers", "47.115.158.112:9092");
        properties.put("acks", "all");
        properties.put("retries", 0);
        properties.put("batch.size", 16384);
        properties.put("linger.ms", 1);
        properties.put("buffer.memory", 33554432);
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, String> kafkaProducer = new KafkaProducer<>(properties);

        for(int i = 0; i < 100; i++) {
            kafkaProducer.send(new ProducerRecord<String, String>("test", Integer.toString(i), Integer.toString(i)));
        }
        kafkaProducer.close();


    }

}
