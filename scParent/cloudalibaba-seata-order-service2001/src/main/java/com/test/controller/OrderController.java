package com.test.controller;


import com.alibaba.fastjson.JSON;
import com.test.entity.Order;
import com.test.entity.R;
import com.test.service.AccountService;
import com.test.service.OrderService;
import com.test.service.StorageService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
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
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Resource
    private AccountService accountService;

    @Resource
    private StorageService storageService;

    @GetMapping("/process")
    @GlobalTransactional(name = "default_order_storage_account", rollbackFor = Exception.class)
    public R process(){

        //1 创建订单
        Order order = new Order();
        order.setCount(1);
        order.setMoney(new BigDecimal(100));
        order.setProductId(1L);
        order.setStatus(0);
        order.setUserId(1L);

        //orderService.saveOne();

        //orderService.saveOrUpdate(order);
        orderService.save(order);

        //2 扣减库存
        Map<String, Object> param = new HashMap<>();
        param.put("productId", order.getProductId());
        param.put("used", order.getCount());
        String JSONStr = JSON.toJSONString(param);
        R r = storageService.process(JSONStr);
        log.info(r.toString());

        //3 扣减账户余额
        Map<String, Object> paramA = new HashMap<>();
        paramA.put("userId", order.getUserId());
        paramA.put("moeny", order.getMoney());
        String JSONStrA = JSON.toJSONString(paramA);
        R r1 = accountService.process(JSONStrA);
        log.info(r1.toString());

        //4 修改订单状态
        order.setStatus(1);
        orderService.update(order, null);


        return new R(200, "ok", null);
    }


}

