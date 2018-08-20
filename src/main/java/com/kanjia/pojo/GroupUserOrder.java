package com.kanjia.pojo;

import java.util.Date;

public class GroupUserOrder {
    private Integer id;

    private Integer userId;

    private Integer groupId;

    private Integer state;

    private String qrCode;

    private String orderNumber;

    private Date createTime;

    private Date updateTime;

    public GroupUserOrder(Integer id, Integer userId, Integer groupId, Integer state, String qrCode, String orderNumber, Date createTime, Date updateTime) {
        this.id = id;
        this.userId = userId;
        this.groupId = groupId;
        this.state = state;
        this.qrCode = qrCode;
        this.orderNumber = orderNumber;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public GroupUserOrder() {
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

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
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