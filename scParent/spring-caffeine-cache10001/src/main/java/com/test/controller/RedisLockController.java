package com.test.controller;

import com.test.lock.RedisLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author ：machunyu
 * @date ：Created in 2022/4/12
 */

@RestController
public class RedisLockController {

    public static final Executor executorService = Executors.newFixedThreadPool(10);

    @Autowired
    private RedisLock redisLock;

    @GetMapping("/redis-lock")
    public String redisLock() {
        for (int i = 0; i < 10; i++) {
            executorService.execute(() -> {

                String name = Thread.currentThread().getName();
                String id = UUID.randomUUID().toString();
                System.out.println(name + "--" + id);

                while (true) {
                    Boolean lock = redisLock.lock(id);
                    if (lock) {
                        try {
                            System.out.println("线程：" + name + "申请加锁成功");
                            //模拟客户端a的操作耗时
                            Thread.sleep(2 * 1000);
                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            Long result = redisLock.unLock(id);
                            if (result == null || result == 0) {
                                System.out.println("线程：" + name + "释放锁失败");
                            } else {
                                System.out.println("线程：" + name + "释放锁成功");
                            }
                        }
                    }
                }
            });
        }
        return "ok";
    }

}
