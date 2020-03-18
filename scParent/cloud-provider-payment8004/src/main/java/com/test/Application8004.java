package com.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


/**
 * @author ：Breeze
 * @date ：Created in 2020/3/11 0:14
 * @description：
 */
@SpringBootApplication
@EnableDiscoveryClient
public class Application8004 {

    public static void main(String[] args) {
        SpringApplication.run(Application8004.class, args);
    }
}
