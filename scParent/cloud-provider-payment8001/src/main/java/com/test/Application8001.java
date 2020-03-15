package com.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


/**
 * @author ：Breeze
 * @date ：Created in 2020/3/11 0:14
 * @description：
 */
@SpringBootApplication
@EnableEurekaClient
public class Application8001 {

    public static void main(String[] args) {
        SpringApplication.run(Application8001.class, args);
    }
}
