package com.test.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：Breeze
 * @date ：Created in 2020/4/20 21:03
 * @description：
 */

@RestController
public class FlowLimitController {

    @GetMapping("/testA")
    public String testA(){
        return "------>testA";
    }

    @GetMapping("/testB")
    public String testB(){
        return "------>testB";
    }

    @GetMapping("/testC")
    public String testC(){
        int a = 10/0;
        return "------>testC";
    }

}
