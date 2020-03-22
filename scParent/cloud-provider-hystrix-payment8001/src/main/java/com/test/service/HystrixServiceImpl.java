package com.test.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author ：Breeze
 * @date ：Created in 2020/3/22 18:59
 * @description：
 */

@Service
public class HystrixServiceImpl {

    public String hystrixOK(Long id) {
        return "hystrixOK-----线程号： " + Thread.currentThread().getName() + "   id号：" + id;
    }

    @HystrixCommand(fallbackMethod = "hystrixTimeoutFallback",commandProperties = {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value = "5000")
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

}
