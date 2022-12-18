package cn.linjk.jarvis.common.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Copyright 1990-2020 LinJK
 *
 * @fileName: DeviceExistsValidatorImpl
 * @author: linjk
 * @date: 2022/12/18 下午1:35
 * @description:
 */
public class DeviceValidator implements ConstraintValidator<Device, String> {
    @Override
    public void initialize(Device constraintAnnotation) {

    }

    /**
     * 校验设备号是否存在
     */
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return false;
    }
}
