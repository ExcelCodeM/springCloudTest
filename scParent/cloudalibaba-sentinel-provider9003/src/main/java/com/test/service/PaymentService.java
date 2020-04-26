package com.test.service;

import com.test.entity.Payment;

/**
 * @author ：Breeze
 * @date ：Created in 2020/3/12 23:29
 * @description：
 */
public interface PaymentService {

    public int savePayment(Payment paymrnt);

    public Payment getPaymentById(Long id);
}
