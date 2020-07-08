package com.test.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：Breeze
 * @date ：Created in 2020/7/1 22:19
 * @description：
 */

@RestController
public class ResourceController {

    @RequestMapping("/resource")
    public String resource(){
        return "访问成功";
    }
}
