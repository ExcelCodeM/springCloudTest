package com.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author ：Breeze
 * @date ：Created in 2020/4/16 22:32
 * @description：
 */
@SpringBootApplication
@EnableDiscoveryClient
public class Application83 {

    public static void main(String[] args) {
        SpringApplication.run(Application83.class,args);
    }

}
