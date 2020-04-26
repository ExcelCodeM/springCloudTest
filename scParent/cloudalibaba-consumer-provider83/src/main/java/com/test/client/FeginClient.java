package com.test.client;

import com.test.client.impl.FeginClientImpl;
import com.test.entity.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author ：Breeze
 * @date ：Created in 2020/4/26 23:33
 * @description：
 */

@FeignClient(value = "sentinel-payment-provider", fallback = FeginClientImpl.class)
public interface FeginClient {


    @GetMapping("/paymentSQL/{id}")
    public R paymentSQL(@PathVariable("id") Long id);

}
