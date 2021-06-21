package com.test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author ：Breeze
 * @date ：Created in 2021/6/19 14:38
 * @description：
 */

class Airconditioner {

    private int number;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void increment() throws Exception {
        lock.lock();
        try {
            //1,判断
            while (number != 0) {
                condition.await();
            }
            //2,干活
            number++;
            System.out.println(Thread.currentThread().getName() + "\t" + number);
            //3,通知
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void decrement() throws Exception {
        lock.lock();
        try {
            while (number == 0) {
                condition.await();
            }
            number--;
            System.out.println(Thread.currentThread().getName() + "\t" + number);
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

}


/**
 * 题目：两个线程可以操作同一个初始值为0的变量，一个线程使变量加1，一个线程使变量减1
 * 相互交替，循环10轮，变量值仍为0
 * <p>
 * 高内聚低耦合，线程操作资源类
 * 判断/干活/通知
 * 多线程交互时，必须防止多线程的虚假唤醒，不允许使用if判断，要用while
 */
public class ThreadWaitNotity {

    public static void main(String[] args) {
        Airconditioner airconditioner = new Airconditioner();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    airconditioner.increment();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    airconditioner.increment();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "C").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    airconditioner.decrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "D").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    airconditioner.decrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "B").start();
    }
}
