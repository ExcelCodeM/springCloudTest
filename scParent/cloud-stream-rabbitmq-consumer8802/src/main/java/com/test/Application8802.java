package com.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author ：Breeze
 * @date ：Created in 2020/4/14 23:26
 * @description：
 */

@SpringBootApplication
@EnableEurekaClient
public class Application8802 {

    public static void main(String[] args) {
        SpringApplication.run(Application8802.class,args);
    }
}
