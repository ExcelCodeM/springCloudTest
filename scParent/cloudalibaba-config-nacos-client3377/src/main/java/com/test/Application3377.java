package com.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author ：Breeze
 * @date ：Created in 2020/4/18 23:27
 * @description：
 */

@SpringBootApplication
@EnableDiscoveryClient
public class Application3377 {

    public static void main(String[] args) {
        SpringApplication.run(Application3377.class,args);
    }
}
