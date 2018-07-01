package com.kanjia.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class Refund {
    private Integer id;

    private Integer userId;

    private Integer orderId;

    private BigDecimal price;

    private Date createTime;

    private Date updateTime;

    private Integer state;

    public Refund(Integer id, Integer userId, Integer orderId, BigDecimal price, Date createTime, Date updateTime, Integer state) {
        this.id = id;
        this.userId = userId;
        this.orderId = orderId;
        this.price = price;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.state = state;
    }

    public Refund() {
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
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