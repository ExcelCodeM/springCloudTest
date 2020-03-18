package com.test.controller;

import com.test.entity.Payment;
import com.test.entity.R;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * @author ：Breeze
 * @date ：Created in 2020/3/12 23:34
 * @description：
 */

@RestController
public class PaymentController {


    @Value("${server.port}")
    private String serverPort;

    @PostMapping(value = "/payment/savePayment")
    public R savePayment(@RequestBody Payment payment){
        //System.out.println("插入成功...");
        return new R(200,"插入成功, serverPort"+serverPort);
    }

    @GetMapping(value = "/payment/get/{id}")
    public R getPayment(@PathVariable("id") Long id){
        //Payment payment = paymentService.getPaymentById(id);
        System.out.println("插入成功...");
        return new R(200,"查询成功, serverPort"+serverPort, UUID.randomUUID());
        /*if(payment != null) {
            return new R(200,"查询成功, serverPort"+serverPort,payment);
        }else{
            return new R(444,"查询失败",null);
        }*/
    }

}
