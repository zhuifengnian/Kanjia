package com.kanjia.pojo;

import java.util.Date;

public class Activity {
    private Integer id;

    private String title;

    private String picture;

    private String video;

    private String adress;

    private String originPrice;

    private String nowPrice;

    private Date activityTime;

    private Integer enterpriseId;

    private Integer limitNumber;

    private Integer soldNumber;

    private Date consumeTime;

    private Date cutTime;

    private Integer state;

    private Date createTime;

    private Date updateTime;

    private Integer categoryId;

    private String longitude;

    private String latitude;

    private String description;

    public Activity(Integer id, String title, String picture, String video, String adress, String originPrice, String nowPrice, Date activityTime, Integer enterpriseId, Integer limitNumber, Integer soldNumber, Date consumeTime, Date cutTime, Integer state, Date createTime, Date updateTime, Integer categoryId, String longitude, String latitude, String description) {
        this.id = id;
        this.title = title;
        this.picture = picture;
        this.video = video;
        this.adress = adress;
        this.originPrice = originPrice;
        this.nowPrice = nowPrice;
        this.activityTime = activityTime;
        this.enterpriseId = enterpriseId;
        this.limitNumber = limitNumber;
        this.soldNumber = soldNumber;
        this.consumeTime = consumeTime;
        this.cutTime = cutTime;
        this.state = state;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.categoryId = categoryId;
        this.longitude = longitude;
        this.latitude = latitude;
        this.description = description;
    }

    public Activity() {
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

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video == null ? null : video.trim();
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress == null ? null : adress.trim();
    }

    public String getOriginPrice() {
        return originPrice;
    }

    public void setOriginPrice(String originPrice) {
        this.originPrice = originPrice == null ? null : originPrice.trim();
    }

    public String getNowPrice() {
        return nowPrice;
    }

    public void setNowPrice(String nowPrice) {
        this.nowPrice = nowPrice == null ? null : nowPrice.trim();
    }

    public Date getActivityTime() {
        return activityTime;
    }

    public void setActivityTime(Date activityTime) {
        this.activityTime = activityTime;
    }

    public Integer getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Integer enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public Integer getLimitNumber() {
        return limitNumber;
    }

    public void setLimitNumber(Integer limitNumber) {
        this.limitNumber = limitNumber;
    }

    public Integer getSoldNumber() {
        return soldNumber;
    }

    public void setSoldNumber(Integer soldNumber) {
        this.soldNumber = soldNumber;
    }

    public Date getConsumeTime() {
        return consumeTime;
    }

    public void setConsumeTime(Date consumeTime) {
        this.consumeTime = consumeTime;
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

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude == null ? null : longitude.trim();
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude == null ? null : latitude.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
}