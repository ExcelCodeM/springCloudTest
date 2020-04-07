package com.test.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ：Breeze
 * @date ：Created in 2020/4/6 22:40
 * @description：
 */

//@Configuration  //演示yml配置方式，动态路由配置，注释掉@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customRouteLicator(RouteLocatorBuilder builder) {
        return builder.routes().route("payment_id", r -> r.path("/guonei").uri("http://news.baidu.com/guonei")).build();
    }
}
