package com.test.abstractfactory;

public class MacOSCheckBox implements CheckBox {
    @Override
    public void onclick() {
        System.out.println("onclick MacOS checkBox");
    }
}
