package com.test.test;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author ：Breeze
 * @date ：Created in 2020/6/29 23:28
 * @description：
 */
public class Test {

    public static void main(String[] args) {
        System.out.println(new BCryptPasswordEncoder().encode("123456"));
        System.out.println(new BCryptPasswordEncoder().encode("secret"));
    }
}
