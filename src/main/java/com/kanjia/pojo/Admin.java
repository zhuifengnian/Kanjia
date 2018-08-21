package com.kanjia.pojo;

import java.util.Date;

public class Admin {
    private Integer id;

    private String nickname;

    private String avatarurl;

    private String openId;

    private Integer state;

    private Integer identity;

    private Date createTime;

    private Date updateTime;

    public Admin(Integer id, String nickname, String avatarurl, String openId, Integer state, Integer identity, Date createTime, Date updateTime) {
        this.id = id;
        this.nickname = nickname;
        this.avatarurl = avatarurl;
        this.openId = openId;
        this.state = state;
        this.identity = identity;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Admin() {
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

    public String getAvatarurl() {
        return avatarurl;
    }

    public void setAvatarurl(String avatarurl) {
        this.avatarurl = avatarurl == null ? null : avatarurl.trim();
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId == null ? null : openId.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getIdentity() {
        return identity;
    }

    public void setIdentity(Integer identity) {
        this.identity = identity;
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