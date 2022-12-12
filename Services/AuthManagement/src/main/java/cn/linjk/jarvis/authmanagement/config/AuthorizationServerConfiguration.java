package cn.linjk.jarvis.authmanagement.config;

/**
 * Copyright 1990-2020 LinJK
 *
 * @fileName: AuthorizationServerConfiguration
 * @author: linjk
 * @date: 2022/4/11 下午9:22
 * @description: 授权服务
 */

import cn.linjk.jarvis.authmanagement.service.DeviceAuthenticServiceImpl;
import cn.linjk.jarvis.common.bean.SignInIdentity;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import javax.annotation.Resource;
import java.util.LinkedHashMap;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {
    @Resource private RedisTokenStore redisTokenStore;
    @Resource private AuthenticationManager authenticationManager;
    @Resource private PasswordEncoder passwordEncoder;
    @Resource private ClientOAuth2DataConfiguration clientOAuth2DataConfiguration;
    @Resource private DeviceAuthenticServiceImpl userService;

    /**
     * 配置令牌端点安全约束
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security
                // 允许访问 token 的公钥，默认 /oauth/token_key 是受保护的
                .tokenKeyAccess("permitAll()")
                // 允许检查 token 的状态，默认 /oauth/check_token 是受保护的
                .checkTokenAccess("permitAll()");
    }

    /**
     * 客户端配置 - 授权模型
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                // 客户端标识 ID
                .withClient(clientOAuth2DataConfiguration.getClientId())
                // 客户端安全码
                .secret(passwordEncoder.encode(clientOAuth2DataConfiguration.getSecret()))
                // 授权类型
                .authorizedGrantTypes(clientOAuth2DataConfiguration.getGrantTypes())
                // token 有效期
                .accessTokenValiditySeconds(clientOAuth2DataConfiguration.getTokenValidityTime())
                // 刷新 token 的有效期
                .refreshTokenValiditySeconds(clientOAuth2DataConfiguration.getRefreshTokenValidityTime())
                // 客户端访问范围
                .scopes(clientOAuth2DataConfiguration.getScopes());
    }

    /**
     * 配置授权以及令牌的访问端点和令牌服务
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        // 认证器
        endpoints.authenticationManager(authenticationManager)
                // 具体登录的方法
                .userDetailsService(userService)
                // token 存储的方式：Redis
                .tokenStore(redisTokenStore)
                // 令牌增强对象，增强返回的结果
                .tokenEnhancer((accessToken, authentication) -> {
                    // 获取登录用户的信息
                    SignInIdentity signInIdentity = (SignInIdentity) authentication.getPrincipal();

                    LinkedHashMap<String, Object> map = new LinkedHashMap<>();
                    map.put("deviceName", signInIdentity.getDeviceName());
                    map.put("aliasName", signInIdentity.getAliasName());
                    map.put("avatarUrl", signInIdentity.getAvatarUrl());
                    DefaultOAuth2AccessToken token = (DefaultOAuth2AccessToken) accessToken;
                    token.setAdditionalInformation(map);
                    return token;
                });
    }
}
