package com.test.service.impl;

import com.test.dao.PaymentMapper;
import com.test.entity.Payment;
import com.test.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ：Breeze
 * @date ：Created in 2020/3/12 23:31
 * @description：
 */

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentMapper paymentMapper;


    @Override
    public int savePayment(Payment paymrnt) {
        return paymentMapper.savePayment(paymrnt);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentMapper.getPaymentById(id);
    }
}
