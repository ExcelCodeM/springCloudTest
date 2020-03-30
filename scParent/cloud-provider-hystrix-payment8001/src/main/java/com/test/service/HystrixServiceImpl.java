package com.test.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;

/**
 * @author ：Breeze
 * @date ：Created in 2020/3/22 18:59
 * @description：
 */

@Service
public class HystrixServiceImpl {

//======================================服务降级=========================================================

    /**
     * @param id
     * @return
     */

    public String hystrixOK(Long id) {
        return "hystrixOK-----线程号： " + Thread.currentThread().getName() + "   id号：" + id;
    }

    @HystrixCommand(fallbackMethod = "hystrixTimeoutFallback", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")
    })
    public String hystrixTimeout(Long id) {
        try {
            Thread.sleep(3000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //System.out.println(new Date().getTime() / 1000);
        return "hystrixTimeout----线程号： " + Thread.currentThread().getName() + "   id号：" + id;
    }


    public String hystrixTimeoutFallback(Long id) {
        return "hystrixTimeoutFallback----线程号： " + Thread.currentThread().getName() + "，当前业务繁忙，请稍后再试";
    }


    //=====================================服务熔断=========================================

    /**
     * 服务熔断
     */
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback", commandProperties = {
            @HystrixProperty(name="circuitBreaker.enabled", value = "true"),  //开启服务熔断
            @HystrixProperty(name="circuitBreaker.requestVolumeThreshold", value = "10"),   //请求次数
            @HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds", value = "10000"),     //时间窗口期  （时间范围）
            @HystrixProperty(name="circuitBreaker.errorThresholdPercentage", value = "60")      //失败率 超过跳闸
    })
    public String paymentCircuitBreaker(Integer id){
        if(id < 0){
            throw new RuntimeException();
        }
        String simpleUUID = IdUtil.simpleUUID();

        return Thread.currentThread().getName()+"\t"+"调用成功，流水号："+simpleUUID;
    }

    public String paymentCircuitBreaker_fallback(Integer id){
        return "id 不能为负数，请稍后再试！！！   id："+id;
    }
}
