package com.kanjia.vo;

import com.kanjia.pojo.DescriptionPicture;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by liyue on 2018/6/29
 */

public class ActivityDescriptionVo {
    private Integer id;

    private String title;

    private String content;

    private List<String> picture;

    private Integer activityId;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<String> getPicture() {
        return picture;
    }

    public void setPicture(List<String> picture) {
        this.picture = picture;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }
}
