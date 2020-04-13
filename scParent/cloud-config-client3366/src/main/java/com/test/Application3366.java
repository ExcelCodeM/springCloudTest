package com.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author ：Breeze
 * @date ：Created in 2020/4/13 22:07
 * @description：
 */

@EnableEurekaClient
@SpringBootApplication
public class Application3366 {

    public static void main(String[] args) {
        SpringApplication.run(Application3366.class, args);
    }
}
