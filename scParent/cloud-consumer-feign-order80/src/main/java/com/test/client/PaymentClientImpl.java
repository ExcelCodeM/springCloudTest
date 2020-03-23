package com.test.client;

import com.test.entity.R;
import org.springframework.stereotype.Component;

/**
 * @author ：Breeze
 * @date ：Created in 2020/3/23 22:52
 * @description：
 */

@Component
public class PaymentClientImpl implements PaymentClient {

    @Override
    public R getPayment(Long id) {
        return new R(200,"error","调用失败");
    }
}
