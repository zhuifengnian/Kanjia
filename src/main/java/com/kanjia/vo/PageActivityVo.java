package com.kanjia.vo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by luyue on 2018/6/29
 */

public class PageActivityVo {
    private Integer id;

    private String title;

    private String picture;

    private String address;

    private BigDecimal originPrice;

    private BigDecimal minuPrice;

    private String enterpriseName;

    private Integer limitNumber;

    private Integer soldNumber;

    private Date cutTime;

    private String longitude;

    private String latitude;

    private Integer types;

    private List<String> headSculpture;

    private Integer headSculptureCount;

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

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }


    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
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


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public Integer getTypes() {
        return types;
    }

    public void setTypes(Integer types) {
        this.types = types;
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
}
