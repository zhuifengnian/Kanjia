package com.kanjia.pojo;

import java.util.Date;

public class HelpUser {
    private Integer id;

    private Integer orderId;

    private Integer userId;

    private Date createTime;

    private String cutPrice;

    public HelpUser(Integer id, Integer orderId, Integer userId, Date createTime, String cutPrice) {
        this.id = id;
        this.orderId = orderId;
        this.userId = userId;
        this.createTime = createTime;
        this.cutPrice = cutPrice;
    }

    public HelpUser() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCutPrice() {
        return cutPrice;
    }

    public void setCutPrice(String cutPrice) {
        this.cutPrice = cutPrice == null ? null : cutPrice.trim();
    }
}