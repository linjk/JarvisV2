package cn.linjk.jarvis.apis;

import cn.linjk.jarvis.common.tables.DeviceInfo;

/**
 * Copyright 1990-2020 LinJK
 *
 * @fileName: IDeviceInfoApi
 * @author: linjk
 * @date: 2022/4/14 上午12:21
 * @description:
 */
public interface IDeviceInfoApi {
    DeviceInfo getDeviceInfo(Long id);
}
