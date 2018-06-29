package com.kanjia.pojo;

import java.util.Date;

public class OrderActivity {
    private Integer id;

    private Integer userId;

    private Integer activityId;

    private Integer state;

    private Date createTime;

    private Date updateTime;

    public OrderActivity(Integer id, Integer userId, Integer activityId, Integer state, Date createTime, Date updateTime) {
        this.id = id;
        this.userId = userId;
        this.activityId = activityId;
        this.state = state;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public OrderActivity() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
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