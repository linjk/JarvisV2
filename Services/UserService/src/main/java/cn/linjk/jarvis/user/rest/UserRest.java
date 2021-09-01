package cn.linjk.jarvis.user.rest;

import cn.linjk.jarvis.api.message.IMessageApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Copyright 1990-2020 LinJK
 *
 * @fileName: UserRest
 * @author: linjk
 * @date: 2021/7/20 上午12:42
 * @description:
 */
@RefreshScope
@RestController
public class UserRest {
    @Autowired private IMessageApi messageApi;

    @Value("${version: null}") private String version;

    @GetMapping("/version")
    public String getVersion() {
        return version;
    }

    @GetMapping("/user-test")
    public String getMessage() {
        boolean result = messageApi.sendMessage(1, "test");
        return result ? "success" : "fail";
    }
}
