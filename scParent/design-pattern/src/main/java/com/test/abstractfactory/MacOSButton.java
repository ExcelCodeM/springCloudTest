package com.test.abstractfactory;

public class MacOSButton implements Buttons {
    @Override
    public void paint() {
        System.out.println("This is MacOS button");
    }
}
