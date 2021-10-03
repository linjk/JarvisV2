package cn.linjk.jarvis.common.entity;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * Copyright 1990-2020 LinJK
 *
 * @fileName: DeviceInfo
 * @author: linjk
 * @date: 2021/10/2 下午9:49
 * @description:
 */
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TargetDevice implements Serializable {
    private String deviceCode;
    private String deviceName;

    private Date serverReceived;
}
