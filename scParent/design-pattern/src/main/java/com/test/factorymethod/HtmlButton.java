package com.test.factorymethod;

/**
 * HTML button 实现
 */
public class HtmlButton implements Buttons {

    @Override
    public void render() {
        System.out.println("this is htmlButton");
        onClick();
    }

    @Override
    public void onClick() {
        System.out.println("onclick html button");
    }
}
