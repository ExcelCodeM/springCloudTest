package com.test.proxy;

/**
 * @author ：machunyu
 * @date ：Created in 2021/12/22
 */
public class Demo {

    public static void main(String[] args) {
        Image image = new ProxyImage("test_10mb.jpg");

        // 图像将从磁盘加载
        image.display();
        System.out.println("---------------");
        // 图像不需要从磁盘加载
        image.display();
    }

}
