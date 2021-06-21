package com.test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author ：Breeze
 * @date ：Created in 2021/6/20 2:11
 * @description：
 */

class ShareResouece {

    private int number = 1;
    private Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();

    public void print5() {
        lock.lock();
        try {
            while (number != 1) {
                condition1.await();
                for (int i = 0; i < 5; i++) {
                    System.out.println(Thread.currentThread().getName() + "\t" + i);
                }
            }
            number = 2;
            condition2.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void print10() {
        lock.lock();
        try {
            while (number != 2) {
                condition2.await();
                for (int i = 0; i < 10; i++) {
                    System.out.println(Thread.currentThread().getName() + "\t" + i);
                }
            }
            number = 3;
            condition3.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void print15() {
        lock.lock();
        try {
            while (number != 3) {
                condition3.await();
                for (int i = 0; i < 15; i++) {
                    System.out.println(Thread.currentThread().getName() + "\t" + i);
                }
            }
            number = 1;
            condition1.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}

/**
 * 多线程之间顺序调用，实现A-B-C
 * 三个线程启动如下：
 * A线程打印5次，B线程打印10次，C线程打印15次
 * 接着
 * A线程打印5次，B线程打印10次，C线程打印15次
 * ......来10轮
 *
 *  1、高内聚低耦合，线程操作资源类
 *  2、判断/干活/通知
 *  3、多线程交互时，必须防止多线程的虚假唤醒，不允许使用if判断，要用while
 *  4、信号量
 */
public class ThreadOrderAcess {
    public static void main(String[] args) {
        ShareResouece shareResouece = new ShareResouece();
        new Thread(()->{
            for (int i = 0; i < 1; i++) {
                shareResouece.print5();
            }
        }, "A").start();
        new Thread(()->{
            for (int i = 0; i < 1; i++) {
                shareResouece.print10();
            }
        }, "B").start();
        new Thread(()->{
            for (int i = 0; i < 1; i++) {
                shareResouece.print15();
            }
        }, "C").start();
    }
}
