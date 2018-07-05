package com.kanjia.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class HelpUser {
    private Integer id;

    private Integer orderId;

    private Integer userId;

    private Date createTime;

    private BigDecimal cutPrice;

    private Integer pick;

    public HelpUser(Integer id, Integer orderId, Integer userId, Date createTime, BigDecimal cutPrice, Integer pick) {
        this.id = id;
        this.orderId = orderId;
        this.userId = userId;
        this.createTime = createTime;
        this.cutPrice = cutPrice;
        this.pick = pick;
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

    public BigDecimal getCutPrice() {
        return cutPrice;
    }

    public void setCutPrice(BigDecimal cutPrice) {
        this.cutPrice = cutPrice;
    }

    public Integer getPick() {
        return pick;
    }

    public void setPick(Integer pick) {
        this.pick = pick;
    }
}