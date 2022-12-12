package cn.linjk.jarvis.common.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Copyright 1990-2020 LinJK
 *
 * @fileName: ResultInfo
 * @author: linjk
 * @date: 2022/4/10 下午6:34
 * @description:
 */
@Getter
@Setter
@ApiModel(value = "返回说明")
public class ResultInfo<T> implements Serializable {
    private static final long serialVersionUID = 4104665778567314378L;

    @ApiModelProperty(value = "成功标识0=失败，1=成功")
    private Integer code;
    @ApiModelProperty(value = "描述信息")
    private String message;
    @ApiModelProperty(value = "访问路径")
    private String path;
    @ApiModelProperty(value = "返回数据对象")
    private T data;
}
