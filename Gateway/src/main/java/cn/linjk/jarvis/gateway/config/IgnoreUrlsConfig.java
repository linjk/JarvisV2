package cn.linjk.jarvis.gateway.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Copyright 1990-2020 LinJK
 *
 * @fileName: IgnoreUrlsConfig
 * @author: linjk
 * @date: 2022/4/16 下午7:40
 * @description: 网关白名单配置
 */
@Data
@Component
@ConfigurationProperties(prefix = "secure.ignore")
public class IgnoreUrlsConfig {
    private List<String> urls;
}
