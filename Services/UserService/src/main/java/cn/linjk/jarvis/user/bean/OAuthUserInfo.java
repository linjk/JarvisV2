package cn.linjk.jarvis.user.bean;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * Copyright 1990-2020 LinJK
 *
 * @fileName: OAuthUserInfo
 * @author: linjk
 * @date: 2022/4/12 下午10:04
 * @description:
 */
@Getter
@Setter
public class OAuthUserInfo implements Serializable {
    private static final long serialVersionUID = 7164117196498737047L;

    private String aliasName;
    private String avatarUrl;
    private String accessToken;
    private String expireIn;
    private List<String> scopes;
    private String refreshToken;
}
