package com.test.controller;

import com.test.entity.Payment;
import com.test.entity.R;
import com.test.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author ：Breeze
 * @date ：Created in 2020/4/28 22:59
 * @description：
 */

@RestController
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class TransactionalController {

    @Autowired
    private PaymentService paymentService;


    @GetMapping("/testTransactional")
    public R testTransactional(){
        Payment payment = new Payment(15L,"secuess!!");
        paymentService.savePayment(payment);
//        Payment payment1 = paymentService.getPaymentById(15L);
//        log.info(payment1.toString());
//        log.info("添加成功！！");
//        throw new RuntimeException();
        return new R();
    }

}
