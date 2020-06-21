package com.test.kafka;

import com.alibaba.fastjson.JSON;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author ：Breeze
 * @date ：Created in 2020/6/7 21:12
 * @description：
 */

@Component
public class KafkaConsumer {

    private static Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);

    @KafkaListener(topics = {"hello"})
    public void listen(ConsumerRecord<String, String> record){

        Student student = JSON.parseObject(record.value(), Student.class);
        logger.info("消费者接受到的数据-->" + student);

    }

}
