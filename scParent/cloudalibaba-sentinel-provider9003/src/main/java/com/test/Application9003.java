package com.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author ：Breeze
 * @date ：Created in 2020/4/25 20:13
 * @description：
 */

@SpringBootApplication
@EnableDiscoveryClient
public class Application9003 {

    public static void main(String[] args) {
        SpringApplication.run(Application9003.class, args);
    }
}
