package com.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author ：Breeze
 * @date ：Created in 2020/3/15 17:25
 * @description：
 */
@SpringBootApplication
@EnableEurekaServer
public class Application7002 {

    public static void main(String[] args) {
        SpringApplication.run(Application7002.class,args);
    }

}
