package com.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author ：Breeze
 * @date ：Created in 2020/4/10 0:10
 * @description：
 */
@SpringBootApplication
@EnableEurekaClient
@EnableConfigServer
public class Application3344 {

    public static void main(String[] args) {

        SpringApplication.run(Application3344.class,args);
    }
}
