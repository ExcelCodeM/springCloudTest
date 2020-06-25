package com.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author ：Breeze
 * @date ：Created in 2020/6/22 22:59
 * @description：
 */

@Controller
public class LoginController {

    @GetMapping("login")
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping("success")
    public String success() {
        return "登录成功";
    }

}
