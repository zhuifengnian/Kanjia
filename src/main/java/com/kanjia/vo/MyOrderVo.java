package com.kanjia.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.TreeSet;

/**
 * 我的订单页面的vo<br/>
 * fan 2018/7/4 20:14
 */

public class MyOrderVo {
    private Integer oid;

    private Integer state;

    private String stateName;

    private String title;

    private String picture;

    private Date activityTime;

    private Date cutTime;   //活动截止时间

    private BigDecimal originPrice;

    private  BigDecimal currentPrice;

    private String qrCode;

    private List<String> helperAvatars;

    private Integer helperNum;

    public Integer getOid() {
        return oid;
    }

    public void setOid(Integer oid) {
        this.oid = oid;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
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

    public Date getActivityTime() {
        return activityTime;
    }

    public void setActivityTime(Date activityTime) {
        this.activityTime = activityTime;
    }

    public Date getCutTime() {
        return cutTime;
    }

    public void setCutTime(Date cutTime) {
        this.cutTime = cutTime;
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

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public List<String> getHelperAvatars() {
        return helperAvatars;
    }

    public void setHelperAvatars(List<String> helperAvatars) {
        this.helperAvatars = helperAvatars;
    }

    public Integer getHelperNum() {
        return helperNum;
    }

    public void setHelperNum(Integer helperNum) {
        this.helperNum = helperNum;
    }
}