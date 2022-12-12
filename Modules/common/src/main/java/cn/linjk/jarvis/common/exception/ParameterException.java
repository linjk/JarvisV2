package cn.linjk.jarvis.common.exception;

import cn.linjk.jarvis.common.bean.Constant;
import lombok.Getter;
import lombok.Setter;

/**
 * Copyright 1990-2020 LinJK
 *
 * @fileName: ParameterException
 * @author: linjk
 * @date: 2022/5/29 上午12:31
 * @description:
 */
@Getter
@Setter
public class ParameterException extends RuntimeException {
    private Integer errorCode;

    public ParameterException() {
        super(Constant.ERROR_MESSAGE);
        this.errorCode = Constant.ERROR_CODE;
    }

    public ParameterException(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public ParameterException(String message) {
        super(message);
        this.errorCode = Constant.ERROR_CODE;
    }

    public ParameterException(Integer errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

}
