package com.test.factorymethod;

/**
 * 工厂方法模式
 */

public class FactoryMethodDemo {

    public static void main(String[] args) {
        new HtmlDialog().renderWindow();
        new WindowsDialog().renderWindow();
    }
}
