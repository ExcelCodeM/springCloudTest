package com.test.controller;


import com.test.entity.User;
import com.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Breeze
 * @since 2020-06-25
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @ResponseBody
    @GetMapping("/save")
    public String saveUser(){

        User user = new User();
        user.setUsername("zhangsan");
        user.setPassword("123456");
        user.setFullname("张三");
        user.setMobile("12345678900");
        userService.save(user);

        return "ok";

    }

}

