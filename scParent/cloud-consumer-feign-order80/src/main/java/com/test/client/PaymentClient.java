package com.test.client;

import com.test.entity.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author ：Breeze
 * @date ：Created in 2020/3/22 16:35
 * @description：
 */

@Component
@FeignClient(value = "CLOUD-PAYMENT-SERVICE")
public interface PaymentClient {

    @GetMapping(value = "/payment/get/{id}")
    R getPayment(@PathVariable("id") Long id);
}
