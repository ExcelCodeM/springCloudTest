package com.test.controller;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.test.entity.R;
import com.test.entity.Storage;
import com.test.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Breeze
 * @since 2020-05-01
 */
@RestController
@RequestMapping("/storage")
@Slf4j
public class StorageController {

    @Autowired
    private StorageService storageService;

    @PostMapping("/process")
    public R process(@RequestBody String json){

        log.info(json);

        Map map = JSON.parseObject(json, Map.class);
        Long productId = Long.valueOf(String.valueOf(map.get("productId")));
        int used = (int)map.get("used");

        QueryWrapper<Storage> wrapper = new QueryWrapper<>();
        wrapper.eq("product_id", productId);

        Storage storage = storageService.getOne(wrapper);
        storage.setUsed(storage.getUsed()+used);
        storage.setResidue(storage.getResidue()-used);

        storageService.update(storage, null);

        return new R(200, "ok", null);

    }
}

