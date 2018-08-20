package com.kanjia.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class Activity {
    private Integer id;

    private Integer types;

    private String title;

    private String picture;

    private String video;

    private String address;

    private BigDecimal originPrice;

    private BigDecimal minuPrice;

    private Integer enterpriseId;

    private Date activityTime;

    private Date cutTime;

    private Date putawayTime;

    private Date soldOutTime;

    private Integer limitNumber;

    private Integer soldNumber;

    private Integer categoryId;

    private String longitude;

    private String latitude;

    private Long glance;

    private Integer pick;

    private Integer state;

    private Date createTime;

    private Date updateTime;

    private String description;

    public Activity(Integer id, Integer types, String title, String picture, String video, String address, BigDecimal originPrice, BigDecimal minuPrice, Integer enterpriseId, Date activityTime, Date cutTime, Date putawayTime, Date soldOutTime, Integer limitNumber, Integer soldNumber, Integer categoryId, String longitude, String latitude, Long glance, Integer pick, Integer state, Date createTime, Date updateTime, String description) {
        this.id = id;
        this.types = types;
        this.title = title;
        this.picture = picture;
        this.video = video;
        this.address = address;
        this.originPrice = originPrice;
        this.minuPrice = minuPrice;
        this.enterpriseId = enterpriseId;
        this.activityTime = activityTime;
        this.cutTime = cutTime;
        this.putawayTime = putawayTime;
        this.soldOutTime = soldOutTime;
        this.limitNumber = limitNumber;
        this.soldNumber = soldNumber;
        this.categoryId = categoryId;
        this.longitude = longitude;
        this.latitude = latitude;
        this.glance = glance;
        this.pick = pick;
        this.state = state;
        this.createTime = createTime;
        this.updateTime = updateTime;
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

    public Integer getTypes() {
        return types;
    }

    public void setTypes(Integer types) {
        this.types = types;
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

    public Integer getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Integer enterpriseId) {
        this.enterpriseId = enterpriseId;
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

    public Long getGlance() {
        return glance;
    }

    public void setGlance(Long glance) {
        this.glance = glance;
    }

    public Integer getPick() {
        return pick;
    }

    public void setPick(Integer pick) {
        this.pick = pick;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
}