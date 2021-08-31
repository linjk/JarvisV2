package cn.linjk.jarvis.common.entity;

import java.io.Serializable;

/**
 * Copyright 1990-2020 LinJK
 *
 * @fileName: SkillEntity
 * @author: linjk
 * @date: 2021/8/31 下午11:06
 * @description:
 */
public class SkillEntity implements Serializable {
    private String productId;
    private String userId;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
