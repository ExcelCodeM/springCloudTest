package com.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author ：Breeze
 * @date ：Created in 2020/3/14 16:20
 * @description：
 */
@SpringBootApplication
@EnableEurekaClient
public class Application80 {

    public static void main(String[] args) {
        SpringApplication.run(Application80.class,args);
    }
}
