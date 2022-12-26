package cn.linjk.jarvis.device.rest;

import cn.linjk.jarvis.apis.IDeviceInfoApi;
import cn.linjk.jarvis.common.bean.ResultInfo;
import cn.linjk.jarvis.common.mybatis.entity.DeviceInfo;
import cn.linjk.jarvis.common.util.ResultInfoUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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
public class DeviceManagementRest {

    @Resource private HttpServletRequest request;

    @Value("${version: null}") private String version;
    @Resource private IDeviceInfoApi deviceInfoApi;

    @GetMapping("/version")
    public String getVersion() {
        return version;
    }

    @PostMapping("deviceReg")
    public ResultInfo<DeviceInfo> deviceReg(@RequestBody DeviceInfo deviceInfo) {
        return ResultInfoUtil.buildSuccess(request.getServletPath(), deviceInfoApi.registerDevice(deviceInfo));
    }
}
