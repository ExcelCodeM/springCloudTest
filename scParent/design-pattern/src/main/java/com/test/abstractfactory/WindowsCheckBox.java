package com.test.abstractfactory;

public class WindowsCheckBox implements CheckBox {
    @Override
    public void onclick() {
        System.out.println("onclick windows checkBox");
    }
}
