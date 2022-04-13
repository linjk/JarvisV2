package cn.linjk.jarvis.repos;

import cn.linjk.jarvis.common.tables.DeviceInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Copyright 1990-2020 LinJK
 *
 * @fileName: DeviceInfoRepo
 * @author: linjk
 * @date: 2022/4/11 下午10:03
 * @description:
 */
@Repository
public interface DeviceInfoRepo extends JpaRepository<DeviceInfo, Long> {
    @Query(value = "select * from device_info where device_name = ?1", nativeQuery = true)
    DeviceInfo findDeviceByDeviceNameame(String deviceName);
}
