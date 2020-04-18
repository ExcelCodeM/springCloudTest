package com.test.controller;

import com.test.entity.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author ：Breeze
 * @date ：Created in 2020/4/16 22:41
 * @description：
 */

@RestController
public class ConsumerController {

    @Autowired
    private RestTemplate restTemplate;

    public final static String PROVIDER_URL = "http://nacos-provider";

    @GetMapping("get/{id}")
    public R getResult(@PathVariable("id") Long id){
        return restTemplate.getForObject(PROVIDER_URL +"/get/"+id, R.class);
    }

}
