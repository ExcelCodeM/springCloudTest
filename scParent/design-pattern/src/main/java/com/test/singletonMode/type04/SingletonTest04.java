package com.test.singletonMode.type04;

/**
 * @author ：Breeze
 * @date ：Created in 2020/11/15 20:38
 * @description：
 */
public class SingletonTest04 {

    public static void main(String[] args) {
        Singleton instance = Singleton.getInstance();
    }
}

/**
 * 懒汉，双重检查，既能解决性能问题，又能解决线程不安全的问题
 */
class Singleton {

    private Singleton() {

    }

    private static volatile Singleton INSTANCE;

    public static Singleton getInstance() {

        if (INSTANCE == null) {
            synchronized (Singleton.class) {
                if (INSTANCE == null) {
                    INSTANCE = new Singleton();
                }
            }
        }
        return INSTANCE;
    }

}

