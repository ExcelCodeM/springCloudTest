package com.test.service;

import com.test.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Breeze
 * @since 2020-05-01
 */
public interface OrderService extends IService<Order> {

    public void saveOne();

}
