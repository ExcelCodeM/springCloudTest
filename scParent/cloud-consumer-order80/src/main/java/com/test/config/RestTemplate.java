package com.test.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ：Breeze
 * @date ：Created in 2020/3/14 16:22
 * @description：
 */
@Configuration
public class RestTemplate {

    @Bean
    public org.springframework.web.client.RestTemplate createRestTemplate(){
        return new org.springframework.web.client.RestTemplate();
    }

}

