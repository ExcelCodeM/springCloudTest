package com.test.composite;

import java.awt.*;

/**
 * 组合模式
 */
public class Demo {
    public static void main(String[] args) {
        ImageEditor editor = new ImageEditor();

        editor.loadShapes(
                new Circle(10, 10, 10, Color.BLUE),

                new CompoundShape(
                        new Circle(110, 110, 50, Color.RED),
                        new Square(160, 160, Color.RED)
                ),

                new CompoundShape(
                        new Rectangle(250, 250, 100, 100, Color.GREEN),
                        new Square(240, 240, Color.GREEN),
                        new Square(240, 360, Color.GREEN),
                        new Square(360, 360, Color.GREEN),
                        new Square(360, 240, Color.GREEN)
                )
        );
    }
}