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

    private String video;

    private String adress;

    private Integer pick;

    private Date activityTime;

    private Integer limitNumber;

    private Integer soldNumber;

    private Date cutTime;

    private String description;

    private EnterpriseVo enterprise;



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

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
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
}
