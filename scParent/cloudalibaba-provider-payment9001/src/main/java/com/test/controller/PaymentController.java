package com.test.controller;

import com.test.entity.R;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：Breeze
 * @date ：Created in 2020/4/16 22:36
 * @description：
 */

@RestController
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/get/{id}")
    public R getId(@PathVariable("id") Long id){
        return new R(200,"ok", "id:"+ id +",post:"+ serverPort);
    }

}
