package com.test;

import com.test.lock.RedisLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.UUID;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author ：Breeze
 * @date ：Created in 2020/11/8 23:05
 * @description： redis分布式锁
 */

@SpringBootApplication
public class Application10001 {

    @Autowired
    private RedisLock redisLock;


    public static final Executor executorService = Executors.newFixedThreadPool(10);


    public static void main(String[] args) {

        ApplicationContext run = SpringApplication.run(Application10001.class, args);
        RedisLock redisLock = (RedisLock) run.getBean("redisLock");

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
    }
}
