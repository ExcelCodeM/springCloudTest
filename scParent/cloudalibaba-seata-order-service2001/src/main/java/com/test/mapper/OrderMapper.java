package com.test.mapper;

import com.test.entity.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Breeze
 * @since 2020-05-01
 */
public interface OrderMapper extends BaseMapper<Order> {

    public void saveOne();

}
