package com.test.abstractfactory;

public class MacOSFactory implements GUIFactory {
    @Override
    public Buttons createButton() {
        return new MacOSButton();
    }

    @Override
    public CheckBox createCheckBox() {
        return new MacOSCheckBox();
    }
}
