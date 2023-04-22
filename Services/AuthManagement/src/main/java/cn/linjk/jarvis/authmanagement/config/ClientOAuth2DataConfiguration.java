package cn.linjk.jarvis.authmanagement.config;

/**
 * Copyright 1990-2020 LinJK
 *
 * @fileName: ClientOAuth2DataConfiguration
 * @author: linjk
 * @date: 2022/4/11 下午9:24
 * @description: 客户端配置类
 */

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "client.oauth2")
public class ClientOAuth2DataConfiguration {
    /**
     * 客户端标识 ID
     */
    private String clientId;
    /**
     * 客户端安全码
     */
    private String secret;
    /**
     * 授权类型
     */
    private String[] grantTypes;
    /**
     * token有效期
     */
    private int tokenValidityTime;
    /**
     * refresh-token有效期
     */
    private int refreshTokenValidityTime;
    /**
     * 客户端访问范围
     */
    private String[] scopes;

    private String[] whites;
}
