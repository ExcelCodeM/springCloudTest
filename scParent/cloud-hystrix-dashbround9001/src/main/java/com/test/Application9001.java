package com.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * @author ：Breeze
 * @date ：Created in 2020/3/31 23:33
 * @description：
 */
@SpringBootApplication
@EnableHystrixDashboard
public class Application9001 {

    public static void main(String[] args) {
        SpringApplication.run(Application9001.class, args);
    }
}
