package com.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author ：machunyu
 * @date ：Created in 2022/4/15
 * <p>
 * redis实现简单限流
 * <p>
 * 条件：同一个用户在30分钟内，只能有5次添加行为
 * <p>
 * 使用redis的zset数据结构，score：用当前时间，value: 不重要，用啥都行
 * 可以通过score获取一段时间内的数据个数，与限制条件做比较
 */

@RestController
public class SimpleLimiterController {

    public static final String USER_ID = "1234123";
    public static final String ACTION = "add";
    public static final Integer NUMBER_OR_TIME = 5;
    public static final Integer LIMITER_TIME = 30;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @GetMapping("simple_limiter")
    public String simpleLimiter() {

        String key = USER_ID + ":" + ACTION;

        ZSetOperations<String, Object> zSetOperations = redisTemplate.opsForZSet();

        //删除30分钟以前的用户行为记录
        zSetOperations.removeRangeByScore(key, 0, System.currentTimeMillis() - LIMITER_TIME * 1000);

        //zset中剩余的item，都是30分钟之内的，直接拿数量即可
        Long size = zSetOperations.size(key);

        if (!ObjectUtils.isEmpty(size) && size >= NUMBER_OR_TIME) {
            return "filed, " + LIMITER_TIME + "分钟内操作超过限制";
        }

        zSetOperations.add(key, UUID.randomUUID().toString(), System.currentTimeMillis());

        return "success, 操作已经记录";

    }
}
