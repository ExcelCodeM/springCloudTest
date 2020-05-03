package com.test.service.impl;

import com.test.entity.Order;
import com.test.mapper.OrderMapper;
import com.test.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Breeze
 * @since 2020-05-01
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Override
    public void saveOne() {
        baseMapper.saveOne();
    }
}
