package com.kanjia.pojo;

import java.util.Date;

public class UserOrder {
    private Integer id;

    private Integer userId;

    private Integer orderId;

    private String minuPrice;

    private Integer helpId;

    private Date createTime;

    private Date updateTime;

    private Integer state;

    public UserOrder(Integer id, Integer userId, Integer orderId, String minuPrice, Integer helpId, Date createTime, Date updateTime, Integer state) {
        this.id = id;
        this.userId = userId;
        this.orderId = orderId;
        this.minuPrice = minuPrice;
        this.helpId = helpId;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.state = state;
    }

    public UserOrder() {
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

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getMinuPrice() {
        return minuPrice;
    }

    public void setMinuPrice(String minuPrice) {
        this.minuPrice = minuPrice == null ? null : minuPrice.trim();
    }

    public Integer getHelpId() {
        return helpId;
    }

    public void setHelpId(Integer helpId) {
        this.helpId = helpId;
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

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}