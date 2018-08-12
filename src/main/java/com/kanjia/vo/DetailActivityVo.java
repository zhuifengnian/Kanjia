package com.kanjia.vo;

import com.kanjia.pojo.Enterprise;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by liyue on 2018/6/29
 */

public class DetailActivityVo {
    private Integer id;

    private String title;

    private List<String> picture;

    private String address;

    private Integer pick;

    private Date activityTime;

    private Integer limitNumber;

    private Integer soldNumber;

    private Date cutTime;

    private String description;

    private EnterpriseVo enterprise;

    private BigDecimal originPrice;

    private BigDecimal minuPrice;

    private List<String> headSculpture;

    private Integer headSculptureCount;

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

    public List<String> getPicture() {
        return picture;
    }

    public void setPicture(List<String> picture) {
        this.picture = picture;
    }


    public Integer getPick() {
        return pick;
    }

    public void setPick(Integer pick) {
        this.pick = pick;
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
}
