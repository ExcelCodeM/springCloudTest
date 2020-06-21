package com.test.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

/**
 * @author ：Breeze
 * @date ：Created in 2020/6/9 0:08
 * @description：
 */

@Configuration
@EnableKafka
public class KafkaConfig {

    @Autowired
    private ProducerFactory producerFactory;

//    @Bean
//    public KafkaTemplate kafkaTemplate() {
//        return new KafkaTemplate(producerFactory);
//    }
}
