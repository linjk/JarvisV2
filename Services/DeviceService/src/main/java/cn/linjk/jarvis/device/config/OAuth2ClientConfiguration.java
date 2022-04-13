package cn.linjk.jarvis.device.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Copyright 1990-2020 LinJK
 *
 * @fileName: OAuth2ClientConfiguration
 * @author: linjk
 * @date: 2022/4/12 下午9:53
 * @description: 客户端配置类
 */
@Component
@ConfigurationProperties(prefix = "oauth2.client")
@Getter
@Setter
public class OAuth2ClientConfiguration {
    private String clientId;
    private String secret;
    private String grant_type;
    private String scope;
}
