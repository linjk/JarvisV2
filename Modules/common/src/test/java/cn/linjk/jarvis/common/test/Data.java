package cn.linjk.jarvis.common.test;

import java.io.Serializable;

/**
 * Copyright 1990-2020 LinJK
 *
 * @fileName: Data
 * @author: linjk
 * @date: 2021/9/1 下午10:37
 * @description:
 */
public class Data implements Serializable {
    private Long id;
    private String name;

    public Data() {}

    public Data(Long id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
