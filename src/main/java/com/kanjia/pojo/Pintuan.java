package com.kanjia.pojo;

import java.util.Date;

public class Pintuan {
    private Integer id;

    private Integer activityId;

    private Integer pintuanLeaderId;

    private Integer state;

    private Date createTime;

    private Date updateTime;

    public Pintuan(Integer id, Integer activityId, Integer pintuanLeaderId, Integer state, Date createTime, Date updateTime) {
        this.id = id;
        this.activityId = activityId;
        this.pintuanLeaderId = pintuanLeaderId;
        this.state = state;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Pintuan() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public Integer getPintuanLeaderId() {
        return pintuanLeaderId;
    }

    public void setPintuanLeaderId(Integer pintuanLeaderId) {
        this.pintuanLeaderId = pintuanLeaderId;
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