package com.test.abstractfactory;

/**
 * 产品是Button和checkBox
 * <p>
 * 默认创建了windows和MacOS风格两种
 * <p>
 * 如若以后需要添加新的风格类型，只需要继承GUIFactory，新增工厂即可，不用修改原代码
 * <p>
 * 抽象工厂
 */
public class AbstractFactoryDemo {

    public static void main(String[] args) {
        GUIFactory factory;

        String osName = System.getProperty("os.name").toLowerCase();

        if (osName.contains("mac")) {
            factory = new MacOSFactory();
        } else {
            factory = new WindowsFactory();
        }

        Buttons button = factory.createButton();
        CheckBox checkBox = factory.createCheckBox();

        button.paint();
        checkBox.onclick();

    }
}
