package cn.linjk.jarvis.common.exception;

import cn.linjk.jarvis.common.bean.Constant;
import lombok.Getter;
import lombok.Setter;

/**
 * Copyright 1990-2020 LinJK
 *
 * @fileName: JarvisException
 * @author: linjk
 * @date: 2022/4/10 下午6:28
 * @description:
 */
@Getter
@Setter
public class JarvisException extends RuntimeException {
    private Integer errorCode;

    public JarvisException() {
        super(Constant.ERROR_MESSAGE);
        this.errorCode = Constant.ERROR_CODE;
    }

    public JarvisException(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public JarvisException(String message) {
        super(message);
        this.errorCode = Constant.ERROR_CODE;
    }

    public JarvisException(Integer errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
}
