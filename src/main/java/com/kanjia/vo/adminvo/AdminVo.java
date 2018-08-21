package com.kanjia.vo.adminvo;

import java.util.Date;

/**
 * Created by liyue on 2018/6/29
 */
public class AdminVo {

    private Integer id;

    private String nickname;

    private String avatarurl;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getAvatarurl() {
        return avatarurl;
    }

    public void setAvatarurl(String avatarurl) {
        this.avatarurl = avatarurl;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
