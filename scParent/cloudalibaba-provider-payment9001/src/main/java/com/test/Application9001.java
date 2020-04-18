package com.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author ：Breeze
 * @date ：Created in 2020/4/16 22:00
 * @description：
 */

@SpringBootApplication
@EnableDiscoveryClient
public class Application9001 {

    public static void main(String[] args) {
        SpringApplication.run(Application9001.class, args);
    }
}
