package cn.linjk.jarvis.message.rest;

import cn.linjk.jarvis.apis.IDeviceInfoApi;
import cn.linjk.jarvis.common.mybatis.entity.DeviceInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    @Autowired private IDeviceInfoApi deviceInfoApi;
    @GetMapping("/msg-test/{id}")
    public DeviceInfo test(@PathVariable Long id) {
        return deviceInfoApi.getDeviceInfo(id);
    }
}
