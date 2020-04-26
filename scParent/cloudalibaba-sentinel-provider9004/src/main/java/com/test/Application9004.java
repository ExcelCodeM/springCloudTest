package com.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author ：Breeze
 * @date ：Created in 2020/4/26 20:26
 * @description：
 */

@SpringBootApplication
@EnableDiscoveryClient
public class Application9004 {

    public static void main(String[] args) {
        SpringApplication.run(Application9004.class, args);
    }

}
