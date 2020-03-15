package com.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author ：Breeze
 * @date ：Created in 2020/3/15 16:13
 * @description：
 */

@SpringBootApplication
@EnableEurekaServer
public class Application7001 {

    public static void main(String[] args) {
        SpringApplication.run(Application7001.class,args);
    }

}
