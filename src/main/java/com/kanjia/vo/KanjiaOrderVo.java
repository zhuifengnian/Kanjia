package com.kanjia.vo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 用户正在砍价的订单数据<br/>
 * fan 2018/7/4 16:42
 */
public class KanjiaOrderVo {
    private Integer oid;

    private String title;

    private String picture;

    private BigDecimal originPrice;

    private  BigDecimal currentPrice;

    private BigDecimal minuPrice;

    private Date cutTime;       //活动截止时间

    private Integer helperNum;      //帮砍人数

    public Integer getOid() {
        return oid;
    }

    public void setOid(Integer oid) {
        this.oid = oid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public BigDecimal getOriginPrice() {
        return originPrice;
    }

    public void setOriginPrice(BigDecimal originPrice) {
        this.originPrice = originPrice;
    }

    public BigDecimal getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(BigDecimal currentPrice) {
        this.currentPrice = currentPrice;
    }

    public BigDecimal getMinuPrice() {
        return minuPrice;
    }

    public void setMinuPrice(BigDecimal minuPrice) {
        this.minuPrice = minuPrice;
    }

    public Date getCutTime() {
        return cutTime;
    }

    public void setCutTime(Date cutTime) {
        this.cutTime = cutTime;
    }

    public Integer getHelperNum() {
        return helperNum;
    }

    public void setHelperNum(Integer helperNum) {
        this.helperNum = helperNum;
    }
}