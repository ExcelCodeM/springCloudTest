package com.test.singletonMode.type02;

/**
 * @author ：Breeze
 * @date ：Created in 2020/11/15 20:24
 * @description：
 */
public class SingletonTest02 {

    public static void main(String[] args) {
        Singletion instance = Singletion.getInstance();
    }

}

/**
 * 懒汉模式，使用才创建，但是有隐患，线程不安全
 */
class Singletion {

    private Singletion() {

    }

    private static Singletion INSTANCE;

    public static Singletion getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Singletion();
        }
        return INSTANCE;
    }
}
