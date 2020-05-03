package com.test.controller;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.test.entity.Account;
import com.test.entity.R;
import com.test.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Breeze
 * @since 2020-05-01
 */

@Slf4j
@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/process")
    public R process(@RequestBody String json){

        log.info(json);

        Map map = JSON.parseObject(json, Map.class);
        Long userId = Long.valueOf(String.valueOf(map.get("userId")));
        BigDecimal decimal = new BigDecimal(Integer.parseInt(map.get("moeny").toString()));

        QueryWrapper<Account> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId);

        Account account = accountService.getOne(wrapper);

        account.setUsed(account.getUsed().add(decimal));
        account.setResidue(account.getResidue().subtract(decimal));

        accountService.update(account, null);

        return new R(200, "ok", null);
    }

}

