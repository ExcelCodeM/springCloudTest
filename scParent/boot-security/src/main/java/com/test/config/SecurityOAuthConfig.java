package com.test.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.InMemoryAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
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

    @Autowired
    private AuthenticationManager authenticationManager;

    /**
     * 配置令牌存储方式，本次采用数据库存储
     *
     * @return
     */
    @Bean
    public TokenStore getTokenStore() {
        return new JdbcTokenStore(dataSource);
    }

    /**
     * 配置客户端详情存储方式，本次采用数据库存储
     *
     * @return
     */
    @Bean
    public ClientDetailsService jdbcClientDetails() {
        return new JdbcClientDetailsService(dataSource);
    }

    /**
     * 接口定义了一些操作使得你可以对令牌进行一些必要的管理，令牌可以被用来 加载身份信息，里面包含了这个令牌的相关权限。
     *
     * @return
     */
    @Bean
    public AuthorizationServerTokenServices tokenService() {
        DefaultTokenServices service = new DefaultTokenServices();
        service.setTokenStore(getTokenStore());  //设置令牌存储方式
        service.setClientDetailsService(jdbcClientDetails());   //设置客户端存储方式
        service.setSupportRefreshToken(true);   //是否支持刷新令牌
        service.setAccessTokenValiditySeconds(60 * 60 * 2);     //设置令牌过期时间 2小时
        service.setRefreshTokenValiditySeconds(60 * 60 * 24 * 3);   //设置刷新令牌的有效期 3天
        return service;
    }

    /**
     * 设置授权码服务
     * @return
     */
    @Bean
    public AuthorizationCodeServices authorizationCodeServices() { //设置授权码模式的授权码如何 存取，
        return new JdbcAuthorizationCodeServices(dataSource);
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
        endpoints.tokenStore(getTokenStore())
                .tokenServices(tokenService())
                .authenticationManager(authenticationManager)
                .authorizationCodeServices(authorizationCodeServices())
                .allowedTokenEndpointRequestMethods(HttpMethod.POST);
    }



}
