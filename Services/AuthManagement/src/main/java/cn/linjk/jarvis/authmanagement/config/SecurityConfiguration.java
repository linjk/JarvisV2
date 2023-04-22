package cn.linjk.jarvis.authmanagement.config;

/**
 * Copyright 1990-2020 LinJK
 *
 * @fileName: SecurityConfiguration
 * @author: linjk
 * @date: 2022/4/11 下午9:09
 * @description: Security 配置类
 */

import cn.hutool.crypto.digest.DigestUtil;
import cn.linjk.jarvis.common.bean.RedisKeyConstant;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import javax.annotation.Resource;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Resource private RedisConnectionFactory redisConnectionFactory;
    @Resource private ClientOAuth2DataConfiguration clientOAuth2DataConfiguration;

    /**
     * 初始化 RedisTokenStore 用于将 token 存储至 Redis
     */
    @Bean
    public RedisTokenStore redisTokenStore() {
        RedisTokenStore redisTokenStore = new RedisTokenStore(redisConnectionFactory);
        redisTokenStore.setPrefix(RedisKeyConstant.KEY_PREFIX.getKey());
        return redisTokenStore;
    }

    /**
     * 初始化密码编码器，用 MD5 加密密码
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence rawPassword) {
                return DigestUtil.md5Hex(rawPassword.toString());
            }

            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                return DigestUtil.md5Hex(rawPassword.toString()).equals(encodedPassword);
            }
        };
    }

    /**
     * 初始化认证管理对象
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * 放行和认证规则
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers(clientOAuth2DataConfiguration.getWhites()).permitAll()
                .and()
                .authorizeRequests()
                .anyRequest().authenticated();
    }
}
