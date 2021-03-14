package com.test.singletonMode.type05;

/**
 * @author ：Breeze
 * @date ：Created in 2020/11/15 20:47
 * @description：
 */
public class SingletonTest05 {

    public static void main(String[] args) {
        Singleton instance = Singleton.getInstance();
    }

}

/**
 * 使用静态内部类
 * 静态内部类好处： 1、外部Singleton加载时，静态内部类并不会加载，只有在用到静态内部类时，才回家再
 *                2、类加载时，是线程安全的
 */
class Singleton{

    private Singleton(){

    }

    public static class SingletionInstance{
        public static final Singleton INSTANCE = new Singleton();
    }

    public static Singleton getInstance(){
        return SingletionInstance.INSTANCE;
    }

}
