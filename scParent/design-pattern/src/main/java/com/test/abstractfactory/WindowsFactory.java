package com.test.abstractfactory;

public class WindowsFactory implements GUIFactory {
    @Override
    public Buttons createButton() {
        return new WindowsButton();
    }

    @Override
    public CheckBox createCheckBox() {
        return new WindowsCheckBox();
    }
}
