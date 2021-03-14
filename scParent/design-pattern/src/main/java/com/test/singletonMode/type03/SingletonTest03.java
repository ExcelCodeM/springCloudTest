package com.test.singletonMode.type03;

/**
 * @author ：Breeze
 * @date ：Created in 2020/11/15 20:24
 * @description：
 */
public class SingletonTest03 {

    public static void main(String[] args) {
        Singletion instance = Singletion.getInstance();
    }

}

/**
 * 懒汉模式，使用才创建，线程安全，但是效率低
 */
class Singletion {

    private Singletion() {

    }

    private static Singletion INSTANCE;

    public static synchronized Singletion getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Singletion();
        }
        return INSTANCE;
    }
}
