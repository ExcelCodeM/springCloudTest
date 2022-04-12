package com.test.lock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * @author ：machunyu
 * @date ：Created in 2022/4/12
 * <p>
 * 分布式锁
 */

@Component
public class RedisLock {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private static final String LOCK = "redis-lock";
    private static final Integer EXPIRE_TIME = 1000;
    private static final TimeUnit TIME_UNIT = TimeUnit.SECONDS;


    public Boolean lock(String value) {
        return redisTemplate.opsForValue().setIfAbsent(LOCK, value, EXPIRE_TIME, TimeUnit.SECONDS);
//        if (flag == null || !flag) {
//            System.out.println("线程：" + name + "申请加锁失败");
//            return false;
//        }
//        System.out.println("线程：" + name + "申请加锁成功");
//        return true;
    }

    public Long unLock(String value) {
        String script = "if redis.call('get', 'redis-lock') == KEYS[1] then return redis.call('del', 'redis-lock') else return 0 end";
        RedisScript<Long> redisScript = new DefaultRedisScript<>(script, Long.class);
        return redisTemplate.execute(redisScript, Arrays.asList(value));

//        if (result == null || result == 0) {
//            System.out.println("线程：" + name + "释放锁失败");
//        } else {
//            System.out.println("线程：" + name + "释放锁成功");
//        }
    }

}
