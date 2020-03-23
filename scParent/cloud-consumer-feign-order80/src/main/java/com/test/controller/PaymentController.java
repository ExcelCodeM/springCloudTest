package com.test.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.test.client.PaymentClient;
import com.test.entity.Payment;
import com.test.entity.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：Breeze
 * @date ：Created in 2020/3/22 16:37
 * @description：
 */

@RestController
//@DefaultProperties(defaultFallback = "global_fallbackMethod", commandProperties = {
//        @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value= "5000")
//})
public class PaymentController {

    @Autowired
    private PaymentClient paymentClient;

    @GetMapping("/consumer/payment/{id}")
    //正常的fallback，但是每一个接口都需要一个fallback，处理起来费事，配置全局fallback，类头添加@DefaultProperties
//    @HystrixCommand(fallbackMethod = "fallbackMethod",commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value= "1500")
//    })
   // @HystrixCommand
    public R getPayment(@PathVariable("id") Long id){
        return paymentClient.getPayment(id);
    }


    public R fallbackMethod(@PathVariable("id") Long id){
        return new R(200,"error","8001调用超时，或80本身报错");
    }

    public R global_fallbackMethod(){
        return new R(200,"error","8001调用超时，或80本身报错,全局fallback");
    }
}
