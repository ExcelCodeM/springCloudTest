package com.test.controller;

import com.test.entity.Payment;
import com.test.entity.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author ：Breeze
 * @date ：Created in 2020/3/14 16:25
 * @description：
 */
@RestController
public class PaymentController {

    @Autowired
    private RestTemplate restTemplate;

    private final String PROVIDER_URL = "http://CLOUD-PAYMENT-SERVICE";

    @GetMapping("/consumer/payment/{id}")
    public R<Payment> getPayment(@PathVariable("id") Long id){
        return restTemplate.getForObject(PROVIDER_URL + "/payment/get/" + id, R.class);
    }

    @GetMapping("/consumer/payment/create")
    public R<Payment> create(Payment payment){
        return restTemplate.postForObject(PROVIDER_URL+"/payment/savePayment",payment,R.class);
    }

}
