package com.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

/**
 * @author ：Breeze
 * @date ：Created in 2020/3/22 18:57
 * @description：
 */

@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker
//@EnableHystrix
public class ApplicationHystrix8001 {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationHystrix8001.class,args);
    }
}
