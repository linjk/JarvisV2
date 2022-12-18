package cn.linjk.jarvis.common.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Copyright 1990-2020 LinJK
 *
 * @fileName: DeviceExistsValidator
 * @author: linjk
 * @date: 2022/12/18 下午1:30
 * @description:
 */
@Documented
@Constraint(validatedBy = DeviceValidator.class)
@Target({METHOD, FIELD})
@Retention(RUNTIME)
public @interface Device {
    /**
     * @return 校验失败的提示语
     */
    String message() default "设备信息无效";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    @Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER})
    @Retention(RUNTIME)
    @Documented
    @interface Lists {
        Device[] value();
    }
}
