package com.test;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * @author ：Breeze
 * @date ：Created in 2021/6/22 0:15
 * @description：
 */

class MyThread implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        System.out.println("****** callable *******");
        return 1024;
    }
}

/**
 * callable 和 runnable的区别
 * 1、方法名称不同
 * 2、有无异常
 * 3、有无返回值
 *
 */

public class CallableDemo {

    public static void main(String[] args) throws Exception {

        FutureTask futureTask = new FutureTask(new MyThread());

        new Thread(futureTask, "A").start();

        System.out.println(futureTask.get());

    }
}
