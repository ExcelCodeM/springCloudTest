package com.test.controller;

import com.test.entity.R;
import com.test.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：Breeze
 * @date ：Created in 2020/4/25 20:19
 * @description：
 */

@RestController
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/paymentSQL/{id}")
    public R paymentSQL(@PathVariable("id") Long id){
        return new R(200, "ok", paymentService.getPaymentById(id) + serverPort);
    }
}
