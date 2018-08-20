package com.kanjia.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class Group {
    private Integer id;

    private Integer activityId;

    private BigDecimal currentPrice;

    private Integer groupLeaderId;

    private Integer state;

    private Date createTime;

    private Date updateTime;

    public Group(Integer id, Integer activityId, BigDecimal currentPrice, Integer groupLeaderId, Integer state, Date createTime, Date updateTime) {
        this.id = id;
        this.activityId = activityId;
        this.currentPrice = currentPrice;
        this.groupLeaderId = groupLeaderId;
        this.state = state;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Group() {
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

    public BigDecimal getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(BigDecimal currentPrice) {
        this.currentPrice = currentPrice;
    }

    public Integer getGroupLeaderId() {
        return groupLeaderId;
    }

    public void setGroupLeaderId(Integer groupLeaderId) {
        this.groupLeaderId = groupLeaderId;
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