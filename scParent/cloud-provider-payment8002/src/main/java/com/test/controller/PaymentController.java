package com.test.controller;

import com.test.entity.Payment;
import com.test.entity.R;
import com.test.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

/**
 * @author ：Breeze
 * @date ：Created in 2020/3/12 23:34
 * @description：
 */

@RestController
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @PostMapping(value = "/payment/savePayment")
    public R savePayment(@RequestBody Payment payment){
        int result = paymentService.savePayment(payment);
        //System.out.println("插入成功...");
        if(result>0) {
            return new R(200,"插入成功, serverPort"+serverPort,result);
        }else{
            return new R(444,"插入失败",result);
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public R getPayment(@PathVariable("id") Long id){
        Payment payment = paymentService.getPaymentById(id);
        System.out.println("插入成功...");
        if(payment != null) {
            return new R(200,"查询成功, serverPort"+serverPort,payment);
        }else{
            return new R(444,"查询失败",null);
        }
    }

}
