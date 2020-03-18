package com.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author ：Breeze
 * @date ：Created in 2020/3/18 23:33
 * @description：
 */

@SpringBootApplication
@EnableDiscoveryClient
public class Application8006 {

    public static void main(String[] args) {
        SpringApplication.run(Application8006.class,args);
    }

}
