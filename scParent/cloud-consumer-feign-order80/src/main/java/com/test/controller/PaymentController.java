package com.test.controller;

import com.test.client.PaymentClient;
import com.test.entity.Payment;
import com.test.entity.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：Breeze
 * @date ：Created in 2020/3/22 16:37
 * @description：
 */

@RestController
public class PaymentController {

    @Autowired
    private PaymentClient paymentClient;

    @GetMapping("/consumer/payment/{id}")
    public R<Payment> getPayment(@PathVariable("id") Long id){
        return paymentClient.getPayment(id);
    }

}
