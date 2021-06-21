package com.test;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author ：Breeze
 * @date ：Created in 2021/6/21 0:29
 * @description：
 */

/**
 * 线程安全的集合
 * 1、new Vector();
 * 2、Collections.synchronizedList(new ArrayList<>());
 * 3、new CopyOnWriteArrayList();
 */
public class NoSafeList {

    static final List<String> list = new CopyOnWriteArrayList<>();

    public static void main(String[] args) {

        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(list);
            }, "i").start();
        }
    }
}
