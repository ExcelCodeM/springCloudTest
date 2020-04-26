package com.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author ：Breeze
 * @date ：Created in 2020/4/20 20:50
 * @description：
 */

@SpringBootApplication
@EnableDiscoveryClient
public class Application8401 {

    public static void main(String[] args) {
        SpringApplication.run(Application8401.class, args);
    }

}
