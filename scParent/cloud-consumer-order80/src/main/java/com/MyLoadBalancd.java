package com;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ：Breeze
 * @date ：Created in 2020/3/19 23:50
 * @description：
 */
@Configuration
public class MyLoadBalancd {

    @Bean
    public IRule myRrle(){

        return new RandomRule();

    }


}
