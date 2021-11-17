package com.test.factorymethod;

public class WindowsDialog extends Dialog {

    @Override
    public Buttons createButton() {
        return new WindowsButton();
    }
}
