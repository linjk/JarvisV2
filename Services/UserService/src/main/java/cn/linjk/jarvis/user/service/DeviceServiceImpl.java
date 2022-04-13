package cn.linjk.jarvis.user.service;

import cn.linjk.jarvis.api.message.IDeviceInfoApi;
import cn.linjk.jarvis.common.tables.DeviceInfo;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * Copyright 1990-2020 LinJK
 *
 * @fileName: DeviceServiceImpl
 * @author: linjk
 * @date: 2022/4/14 上午12:28
 * @description:
 */
@DubboService(version = "1.0.0", group = "user-service")
public class DeviceServiceImpl implements IDeviceInfoApi {
    @Override
    public DeviceInfo getDeviceInfo(Long id) {
        DeviceInfo deviceInfo = new DeviceInfo();
        deviceInfo.setId(id);
        return deviceInfo;
    }
}
