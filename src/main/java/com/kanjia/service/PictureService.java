package com.kanjia.service;

import com.kanjia.pojo.Picture;

import java.util.List;

/**
 * liyue 2018.6.29
 */
public interface PictureService extends BaseService<Picture> {

    /**
     * 通过activity_id获取picture列表
     *
     * @param activityId
     * @return
     */
    List<String> getPicture(Integer activityId);

}