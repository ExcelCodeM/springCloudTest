package com.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author ：Breeze
 * @date ：Created in 2020/4/12 23:23
 * @description：
 */

@SpringBootApplication
@EnableEurekaClient
public class Application3355 {

    public static void main(String[] args) {
        SpringApplication.run(Application3355.class,args);
    }
}
