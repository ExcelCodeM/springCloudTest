package com.test.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        /*
         * 配置用户名的 三种 方式
         * 1、在 application.properties 里面指定
         *        spring.security.user.name=zhangsan
                  spring.security.user.password=123
           2、在 WebSecurityConfigurerAdapter 的 授权认证器中 配置用户名密码
                  auth.inMemoryAuthentication().withUser("zhangsan2").password(new BCryptPasswordEncoder().encode("123"));
           3、
         */
        auth.inMemoryAuthentication().withUser("zhangsan2").password(new BCryptPasswordEncoder().encode("123"));
        super.configure(auth);
    }
}
