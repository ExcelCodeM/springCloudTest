package com.test;

import com.MyLoadBalancd;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

/**
 * @author ：Breeze
 * @date ：Created in 2020/3/14 16:20
 * @description：
 */
@SpringBootApplication
@EnableEurekaClient
@RibbonClient(name = "CLOUD-PAYMENT-SERVICE",configuration = MyLoadBalancd.class)
public class Application80 {

    public static void main(String[] args) {
        SpringApplication.run(Application80.class,args);
    }
}
