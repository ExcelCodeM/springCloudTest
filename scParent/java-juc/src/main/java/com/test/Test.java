package com.test;

/**
 * @author ：Breeze
 * @date ：Created in 2021/7/4 20:38
 * @description：
 */

/**
 * jvm 参数
 * -Xms 初始jvm内存大小，默认为物理内存的 1/64
 * -Xmx 最大分配的jvm内存，默认为物理内存的 1/4
 *
 * -XX:printGCDetails 输出详细的gc日志
 * -XX:MaxTenuringThreshold 设置对象在新生代中存活的次数
 */
public class Test {

    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().availableProcessors());
        System.out.println(Runtime.getRuntime().maxMemory() / 1024 / 1024);
        System.out.println(Runtime.getRuntime().totalMemory() / 1024 / 1024);

    }
}
