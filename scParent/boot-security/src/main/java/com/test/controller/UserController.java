package com.test.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：Breeze
 * @date ：Created in 2020/6/20 19:40
 * @description：
 */

@RestController
public class UserController {

    @GetMapping("testOne")
    public String testOne(){
        return "登录成功！ test1";
    }

    @GetMapping("testTwo")
    public String testTwo(){
        return "登录成功！ test2";
    }

    @GetMapping("testThree")
    public String testThree(){
        return "登录成功！ test3";
    }

    @GetMapping("testFour")
    public String testFour(){
        return "登录成功！ test4";
    }

}
