package com.test;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;

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

    @Bean
    public ServletRegistrationBean hystrixMetricsStreamServlet() {
        ServletRegistrationBean regist = new ServletRegistrationBean();
        regist.setServlet(new HystrixMetricsStreamServlet());
        regist.setName("hystrixMetricsStreamServlet");
        regist.setLoadOnStartup(1);
        regist.addUrlMappings("/hystrix.stream");
        return regist;
    }
}
