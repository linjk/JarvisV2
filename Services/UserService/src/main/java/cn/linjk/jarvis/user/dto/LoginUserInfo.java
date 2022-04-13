package cn.linjk.jarvis.user.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Copyright 1990-2020 LinJK
 *
 * @fileName: LoginUserInfo
 * @author: linjk
 * @date: 2022/4/12 下午10:06
 * @description:
 */
@Getter
@Setter
public class LoginUserInfo implements Serializable {
    private static final long serialVersionUID = 8154408585626876059L;
    private String aliasName;
    private String token;
    private String avatarUrl;
}
