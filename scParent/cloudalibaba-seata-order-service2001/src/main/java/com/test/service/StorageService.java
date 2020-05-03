package com.test.service;

import com.test.entity.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author ：Breeze
 * @date ：Created in 2020/5/2 16:03
 * @description：
 */

@FeignClient("seata-storage-service")
public interface StorageService {

    @PostMapping("/storage/process")
    public R process(String json);

}
