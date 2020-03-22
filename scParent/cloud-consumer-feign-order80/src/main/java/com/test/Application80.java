package com.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author ：Breeze
 * @date ：Created in 2020/3/22 16:33
 * @description：
 */

@EnableEurekaClient
@EnableFeignClients
@SpringBootApplication
public class Application80 {

    public static void main(String[] args) {
        SpringApplication.run(Application80.class,args);
    }
}
