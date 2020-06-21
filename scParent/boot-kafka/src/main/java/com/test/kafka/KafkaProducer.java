package com.test.kafka;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @author ：Breeze
 * @date ：Created in 2020/6/7 20:58
 * @description：
 */

@Component
public class KafkaProducer {

    @Autowired
    public KafkaTemplate kafkaTemplate;

    private static Logger logger = LoggerFactory.getLogger(KafkaProducer.class);

    public void send(){

        Student student = new Student();
        student.setName("张三");
        student.setAge(14);
        String jsonString = JSON.toJSONString(student);

        logger.info("生产的数据-->>" + jsonString);

        kafkaTemplate.send("hello", jsonString);


    }

}

class Student implements Serializable {

    private static final long serialVersionUID = 3482223597240493257L;

    private String name;
    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
