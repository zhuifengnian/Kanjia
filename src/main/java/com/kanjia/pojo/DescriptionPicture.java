package com.kanjia.pojo;

import java.util.Date;

public class DescriptionPicture {
    private Integer id;

    private Integer decriptionId;

    private String picture;

    private Date createTime;

    private Date updateTime;

    public DescriptionPicture(Integer id, Integer decriptionId, String picture, Date createTime, Date updateTime) {
        this.id = id;
        this.decriptionId = decriptionId;
        this.picture = picture;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public DescriptionPicture() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDecriptionId() {
        return decriptionId;
    }

    public void setDecriptionId(Integer decriptionId) {
        this.decriptionId = decriptionId;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture == null ? null : picture.trim();
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
}