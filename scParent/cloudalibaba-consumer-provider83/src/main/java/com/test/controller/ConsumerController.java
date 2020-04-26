package com.test.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.test.entity.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author ：Breeze
 * @date ：Created in 2020/4/16 22:41
 * @description：本controller使用的是rabbin+restTemplate的方式，进行调用
 */

@RestController
public class ConsumerController {

    @Autowired
    private RestTemplate restTemplate;

    //public final static String PROVIDER_URL = "http://nacos-provider";

    public final static String PROVIDER_URL = "http://sentinel-payment-provider";


    /**
     * fallback 属性处理 java代码的错误
     * blockHander 属性处理 sentinel控制台配置错误
     * 如果俩者同时配置，最后会以blockHander为准
     * @param id
     * @return
     */
    @GetMapping("get/{id}")
    @SentinelResource(value = "fallback",fallback = "endMethod")
    public R getResult(@PathVariable("id") Long id){
        R r = restTemplate.getForObject(PROVIDER_URL +"/paymentSQL/"+id, R.class);
        if(r.getData().toString().startsWith("null")){
            throw new RuntimeException();
        }
        return r;
    }


    public R endMethod(@PathVariable("id") Long id, Throwable e){
        return new R(444,"error","兜底方法/(ㄒoㄒ)/~~");
    }


}
