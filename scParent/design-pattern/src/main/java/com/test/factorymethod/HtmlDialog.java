package com.test.factorymethod;

public class HtmlDialog extends Dialog {

    @Override
    public Buttons createButton() {
        return new HtmlButton();
    }
}
