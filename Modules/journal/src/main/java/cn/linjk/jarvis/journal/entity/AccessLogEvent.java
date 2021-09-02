package cn.linjk.jarvis.journal.entity;

import java.util.Date;

/**
 * Copyright 1990-2020 LinJK
 *
 * @fileName: AccessLogEvent
 * @author: linjk
 * @date: 2021/9/1 下午11:55
 * @description:
 */
public class AccessLogEvent {
    public AccessLogEvent() {
        this.date = new Date();
    }

    private String path;
    private Date date;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
