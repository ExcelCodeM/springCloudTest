package com.test.factorymethod;

/**
 * windows button 实现
 */
public class WindowsButton implements Buttons {

    @Override
    public void render() {
        System.out.println("this is windows button");
        onClick();
    }

    @Override
    public void onClick() {
        System.out.println("onclick windows button");
    }
}
