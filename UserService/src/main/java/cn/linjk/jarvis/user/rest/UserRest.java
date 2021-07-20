package cn.linjk.jarvis.user.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Copyright 1990-2020 LinJK
 *
 * @fileName: UserRest
 * @author: linjk
 * @date: 2021/7/20 上午12:42
 * @description:
 */
@RestController
public class UserRest {
    @Autowired private RestTemplate restTemplate;

    @GetMapping("/user-test")
    public String getMessage() {
        String msg = restTemplate.getForObject("http://message-service/msg-test", String.class);
        return msg;
    }
}
