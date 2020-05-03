package com.test.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author ：Breeze
 * @date ：Created in 2020/5/1 22:36
 * @description：
 */

@Configuration
@MapperScan({"com.test.mapper"})
public class MybatisConfig {
}
