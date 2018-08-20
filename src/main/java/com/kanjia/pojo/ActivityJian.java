package com.kanjia.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class ActivityJian {
    private Integer id;

    private String title;

    private String picture;

    private String address;

    private BigDecimal originPrice;

    private BigDecimal minuPrice;

    private Date activityTime;

    private Integer limitNumber;

    private Date putawayTime;

    private Date soldOutTime;

    private Date cutTime;

    private Integer state;

    private String categoryName;



    public ActivityJian() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture == null ? null : picture.trim();
    }



    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public BigDecimal getOriginPrice() {
        return originPrice;
    }

    public void setOriginPrice(BigDecimal originPrice) {
        this.originPrice = originPrice;
    }

    public BigDecimal getMinuPrice() {
        return minuPrice;
    }

    public void setMinuPrice(BigDecimal minuPrice) {
        this.minuPrice = minuPrice;
    }

    public Date getActivityTime() {
        return activityTime;
    }

    public void setActivityTime(Date activityTime) {
        this.activityTime = activityTime;
    }



    public Integer getLimitNumber() {
        return limitNumber;
    }

    public void setLimitNumber(Integer limitNumber) {
        this.limitNumber = limitNumber;
    }



    public Date getCutTime() {
        return cutTime;
    }

    public void setCutTime(Date cutTime) {
        this.cutTime = cutTime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }



    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Date getPutawayTime() {
        return putawayTime;
    }

    public void setPutawayTime(Date putawayTime) {
        this.putawayTime = putawayTime;
    }

    public Date getSoldOutTime() {
        return soldOutTime;
    }

    public void setSoldOutTime(Date soldOutTime) {
        this.soldOutTime = soldOutTime;
    }
}