package com.kanjia.vo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by liyue on 2018/6/29
 */

public class DetailActivityVo {
    private Integer id;

    private String title;

    private String picture;

    private String address;

    private List<String> detailPicture;

    private List<ActivityDescriptionVo> activityDescriptionVos;

    private Date activityTime;

    private Long glance;

    private Integer limitNumber;

    private Integer soldNumber;

    private Date cutTime;

    private String description;

    private EnterpriseVo enterprise;

    private BigDecimal originPrice;

    private BigDecimal minuPrice;

    private List<String> headSculpture;

    private Integer headSculptureCount;

    private Integer categoryId;

    private Integer types;

    private String longitude;

    private String latitude;

    private  List<ActivityDescriptionVo> activityDescription;

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
        this.title = title;
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

    public Integer getSoldNumber() {
        return soldNumber;
    }

    public void setSoldNumber(Integer soldNumber) {
        this.soldNumber = soldNumber;
    }

    public Date getCutTime() {
        return cutTime;
    }

    public void setCutTime(Date cutTime) {
        this.cutTime = cutTime;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public EnterpriseVo getEnterprise() {
        return enterprise;
    }

    public void setEnterprise(EnterpriseVo enterprise) {
        this.enterprise = enterprise;
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

    public List<ActivityDescriptionVo> getActivityDescription() {
        return activityDescription;
    }

    public void setActivityDescription(List<ActivityDescriptionVo> activityDescription) {
        this.activityDescription = activityDescription;
    }

    public List<String> getHeadSculpture() {
        return headSculpture;
    }

    public void setHeadSculpture(List<String> headSculpture) {
        this.headSculpture = headSculpture;
    }

    public Integer getHeadSculptureCount() {
        return headSculptureCount;
    }

    public void setHeadSculptureCount(Integer headSculptureCount) {
        this.headSculptureCount = headSculptureCount;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getTypes() {
        return types;
    }

    public void setTypes(Integer types) {
        this.types = types;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public List<String> getDetailPicture() {
        return detailPicture;
    }

    public void setDetailPicture(List<String> detailPicture) {
        this.detailPicture = detailPicture;
    }

    public List<ActivityDescriptionVo> getActivityDescriptionVos() {
        return activityDescriptionVos;
    }

    public void setActivityDescriptionVos(List<ActivityDescriptionVo> activityDescriptionVos) {
        this.activityDescriptionVos = activityDescriptionVos;
    }

    public Long getGlance() {
        return glance;
    }

    public void setGlance(Long glance) {
        this.glance = glance;
    }
}

