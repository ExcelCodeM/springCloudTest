package com.test.service;

import com.test.entity.R;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author ：Breeze
 * @date ：Created in 2020/5/2 16:44
 * @description：
 */

@FeignClient("seata-account-service")
public interface AccountService {

    @PostMapping("/account/process")
    public R process(String json);

}
