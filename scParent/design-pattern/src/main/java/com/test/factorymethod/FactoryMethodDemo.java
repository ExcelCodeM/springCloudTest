package com.test.factorymethod;

public class FactoryMethodDemo {

    public static void main(String[] args) {
        new HtmlDialog().renderWindow();
        new WindowsDialog().renderWindow();
    }
}
