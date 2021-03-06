package com.kanjia.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class UserOrder {
    private Integer id;

    private Integer userId;

    private Integer activityId;

    private BigDecimal currentPrice;

    private Date createTime;

    private Date updateTime;

    private Integer state;

    private String qrCode;

    private String orderNumber;

    public UserOrder(Integer id, Integer userId, Integer activityId, BigDecimal currentPrice, Date createTime, Date updateTime, Integer state, String qrCode, String orderNumber) {
        this.id = id;
        this.userId = userId;
        this.activityId = activityId;
        this.currentPrice = currentPrice;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.state = state;
        this.qrCode = qrCode;
        this.orderNumber = orderNumber;
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

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode == null ? null : qrCode.trim();
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber == null ? null : orderNumber.trim();
    }
}