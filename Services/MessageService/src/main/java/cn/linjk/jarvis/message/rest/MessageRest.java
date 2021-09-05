package cn.linjk.jarvis.message.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Copyright 1990-2020 LinJK
 *
 * @fileName: MessageRest
 * @author: linjk
 * @date: 2021/7/20 上午12:40
 * @description:
 */
@RestController
public class MessageRest {
    @GetMapping("/msg-test")
    public String test() {
        return "message";
    }
}
