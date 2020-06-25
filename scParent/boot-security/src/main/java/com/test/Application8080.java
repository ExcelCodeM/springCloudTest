package com.test;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author ：Breeze
 * @date ：Created in 2020/6/20 18:17
 * @description：
 */

@SpringBootApplication
@MapperScan("com.test.mapper")
public class Application8080 {

    public static void main(String[] args) {
        SpringApplication.run(Application8080.class, args);
    }
}
