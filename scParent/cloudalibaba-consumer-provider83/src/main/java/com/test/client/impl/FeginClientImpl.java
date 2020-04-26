package com.test.client.impl;

import com.test.client.FeginClient;
import com.test.entity.R;
import org.springframework.stereotype.Component;

/**
 * @author ：Breeze
 * @date ：Created in 2020/4/26 23:37
 * @description：
 */

@Component
public class FeginClientImpl implements FeginClient {
    @Override
    public R paymentSQL(Long id) {
        return new R(400,"error","fegin,统一兜底方法");
    }
}
