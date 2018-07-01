package com.kanjia.pojo;

import java.util.Date;

public class Enterprise {
    private Integer id;

    private String nickname;

    private String openId;

    private String enterpriseName;

    private String license;

    private String corporation;

    private String phone;

    private String card;

    private String avatarurl;

    private Date createTime;

    private Date updateTime;

    private Integer state;

    public Enterprise(Integer id, String nickname, String openId, String enterpriseName, String license, String corporation, String phone, String card, String avatarurl, Date createTime, Date updateTime, Integer state) {
        this.id = id;
        this.nickname = nickname;
        this.openId = openId;
        this.enterpriseName = enterpriseName;
        this.license = license;
        this.corporation = corporation;
        this.phone = phone;
        this.card = card;
        this.avatarurl = avatarurl;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.state = state;
    }

    public Enterprise() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId == null ? null : openId.trim();
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName == null ? null : enterpriseName.trim();
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license == null ? null : license.trim();
    }

    public String getCorporation() {
        return corporation;
    }

    public void setCorporation(String corporation) {
        this.corporation = corporation == null ? null : corporation.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card == null ? null : card.trim();
    }

    public String getAvatarurl() {
        return avatarurl;
    }

    public void setAvatarurl(String avatarurl) {
        this.avatarurl = avatarurl == null ? null : avatarurl.trim();
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

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}