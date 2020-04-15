package com.test.controller;

import com.test.service.IMassageProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：Breeze
 * @date ：Created in 2020/4/14 23:11
 * @description：
 */

@RestController
public class SendMessageController {

    @Autowired
    private IMassageProvider provider;

    @GetMapping("/sendMessage")
    public String sendMessage(){
        return provider.send();
    }
}
