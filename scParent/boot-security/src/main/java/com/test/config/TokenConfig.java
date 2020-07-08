package com.test.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * @author ：Breeze
 * @date ：Created in 2020/7/7 22:15
 * @description：
 */

@Configuration
public class TokenConfig {

    private final static String SIGNING_KEY = "hello123";

    @Bean
    public TokenStore getTokenStore(){
        return new JwtTokenStore(getJwtAccessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter getJwtAccessTokenConverter(){
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey(SIGNING_KEY);
        return converter;
    }

}
