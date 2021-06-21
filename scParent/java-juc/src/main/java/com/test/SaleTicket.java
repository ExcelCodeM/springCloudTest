package com.test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author ：Breeze
 * @date ：Created in 2021/6/19 13:35
 * @description: 复习售票, 企业级多线程套路： 线程  操作  资源类
 */

class Ticket { //资源类

    private int ticket = 300;
    private Lock lock = new ReentrantLock();

    public void saleTicket() {
        lock.lock();
        try {
            if (ticket > 0) {
                System.out.println(Thread.currentThread().getName() + "正出售第" + ticket + "张票,还剩" + --ticket + "张票");
            }
        } finally {
            lock.unlock();
        }
    }

}

public class SaleTicket {

    public static void main(String[] args) {

        Ticket ticket = new Ticket();

        new Thread(() -> {
            for (int i = 0; i < 400; i++) {
                ticket.saleTicket();
            }
        }, "A").start();
        new Thread(() -> {
            for (int i = 0; i < 400; i++) {
                ticket.saleTicket();
            }
        }, "B").start();
        new Thread(() -> {
            for (int i = 0; i < 400; i++) {
                ticket.saleTicket();
            }
        }, "C").start();
    }
}
