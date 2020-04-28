package com.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author ：Breeze
 * @date ：Created in 2020/4/25 20:13
 * @description：
 */

@SpringBootApplication
@EnableDiscoveryClient
@EnableTransactionManagement
public class Application9003 {

    public static void main(String[] args) {
        SpringApplication.run(Application9003.class, args);
    }
}
