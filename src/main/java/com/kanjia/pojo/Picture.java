package com.kanjia.pojo;

import java.util.Date;

public class Picture {
    private Integer id;

    private String url;

    private Integer activityId;

    private Date createTime;

    private Date updateTime;

    public Picture(Integer id, String url, Integer activityId, Date createTime, Date updateTime) {
        this.id = id;
        this.url = url;
        this.activityId = activityId;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Picture() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}