package com.test.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.test.client.FeginClient;
import com.test.entity.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author ：Breeze
 * @date ：Created in 2020/4/26 23:28
 * @description：
 */

/**
 * 本controller使用的是openFegin的方式进行调用
 */
@RestController
public class FeginController {

    @Resource
    private FeginClient feginClient;

    @GetMapping("/get/fegin/{id}")
    public R getResult(@PathVariable("id") Long id) {
        return feginClient.paymentSQL(id);
    }
}
