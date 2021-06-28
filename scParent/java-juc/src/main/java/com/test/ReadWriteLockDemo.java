package com.test;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author ：Breeze
 * @date ：Created in 2021/6/23 22:02
 * @description：
 */

class MyCache {

    private Map<String, Object> cache = new HashMap<>();
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public void write() throws InterruptedException {
        readWriteLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t添加数据...");
            cache.put(Thread.currentThread().getName(), new Object());
            TimeUnit.MILLISECONDS.sleep(100);
            System.out.println(Thread.currentThread().getName() + "\t添加完成...");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.writeLock().unlock();
        }


    }

    public void read() throws InterruptedException {
        readWriteLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t开始读取数据...");
            cache.get(Thread.currentThread().getName());
            TimeUnit.MILLISECONDS.sleep(100);
            System.out.println(Thread.currentThread().getName() + "\t读取完成...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            readWriteLock.readLock().unlock();
        }
    }
}

/**
 * 读写锁，写锁排斥其他操作，读锁只排斥写操作。读锁，读共享
 */
public class ReadWriteLockDemo {

    public static void main(String[] args) {
        MyCache myCache = new MyCache();
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                try {
                    myCache.write();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, String.valueOf(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 8))).start();
        }

        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                try {
                    myCache.read();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, String.valueOf(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 8))).start();
        }
    }
}
