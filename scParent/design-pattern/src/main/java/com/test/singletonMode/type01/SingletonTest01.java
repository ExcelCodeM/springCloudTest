package com.test.singletonMode.type01;

/**
 * @author ：Breeze
 * @date ：Created in 2020/11/15 20:11
 * @description：
 */
public class SingletonTest01 {

    public static void main(String[] args) {
        Singleton instance = Singleton.getInstance();
    }
}

/**
 * 饿汉模式（静态变量）
 */
class Singleton {

    //构造器私有化，在外不能使用new来创建对象
    private Singleton() {

    }

    //本类内部创建对象，类加载时，创建
    private static final Singleton INSTANCE = new Singleton();

    //对外提供获取实例的方法
    public static Singleton getInstance(){
        return INSTANCE;
    }

}
