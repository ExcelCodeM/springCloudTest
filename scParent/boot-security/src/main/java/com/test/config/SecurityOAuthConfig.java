package com.test.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;

/**
 * @author ：Breeze
 * @date ：Created in 2020/6/27 16:30
 * @description：
 */

@Configuration
@EnableAuthorizationServer
public class SecurityOAuthConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Bean
    public TokenStore getTokenStore(){
        return new JdbcTokenStore(dataSource);
    }

    @Bean
    public ClientDetailsService jdbcClientDetails(){
        return new JdbcClientDetailsService(dataSource);
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        //使用内存配置，只是了解入门，改用jdbc配置，数据存入oauth_access_token表中
//        clients.inMemory()  //使用in‐memory存储
//                .withClient("client")   // client_id
//                .secret("secret")
//                .resourceIds("res1")
//                .scopes("all")  // 允许的授权范围
//                    //  "authorization_code", "password","client_credentials","implicit","refresh_token"
//                .authorizedGrantTypes("authorization_code") // 该client允许的授权类型
//                .redirectUris("http://www.baidu.com"); //加上验证回调地址

        clients.withClientDetails(jdbcClientDetails());
    }


    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(getTokenStore());
    }
}
