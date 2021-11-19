package com.test.abstractfactory;

public class WindowsButton implements Buttons {

    @Override
    public void paint() {
        System.out.println("This is windows button");
    }
}
