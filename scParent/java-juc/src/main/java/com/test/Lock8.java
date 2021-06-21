package com.test;

import java.util.concurrent.TimeUnit;

/**
 * @author ：Breeze
 * @date ：Created in 2021/6/20 18:49
 * @description：
 */

class Phone {
    public static synchronized void sendEmail() throws Exception {
        TimeUnit.SECONDS.sleep(4);
        System.out.println("send email...");
    }

    public static synchronized void sendSMS() {
        System.out.println("send SMS...");
    }

    public synchronized void hello() {
        System.out.println("send SMS...");
    }
}

/**
 * 问题：Phone 有两个方法，发送邮件和发送短信，每个方法都打印一句话，现在通过不同的方式对方法进行操作，回答出打印的先后顺序
 * <p>
 * 1、标准访问
 */
public class Lock8 {
    public static void main(String[] args) throws Exception {
        Phone phone = new Phone();
        Phone phone2 = new Phone();
        new Thread(() -> {
            try {
                phone.sendEmail();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "A").start();
        TimeUnit.MILLISECONDS.sleep(200);
        new Thread(() -> phone2.hello(), "A").start();

    }
}
