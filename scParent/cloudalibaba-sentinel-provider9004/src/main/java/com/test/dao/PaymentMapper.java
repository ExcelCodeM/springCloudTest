package com.test.dao;

import com.test.entity.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author ：Breeze
 * @date ：Created in 2020/3/12 23:04
 * @description：
 */
@Mapper
public interface PaymentMapper {

    public int savePayment(Payment payment);

    public Payment getPaymentById(@Param("id") Long id);
}
