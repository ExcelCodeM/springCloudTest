package com.test.factorymethod;

public abstract class Dialog {

    public void renderWindow() {
        Buttons button = createButton();
        button.render();
    }

    public abstract Buttons createButton();

}
