package cn.linjk.jarvis.common.mybatis.mapper;

import cn.linjk.jarvis.common.mybatis.entity.DeviceInfo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface DeviceInfoMapper extends Mapper<DeviceInfo> {
    DeviceInfo findDeviceByDeviceNameame(@Param("deviceName") String deviceName);

    DeviceInfo findDeviceByDeviceId(@Param("id") Long id);
}