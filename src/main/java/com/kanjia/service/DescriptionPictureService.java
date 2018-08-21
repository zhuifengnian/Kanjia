package com.kanjia.service;

import com.kanjia.pojo.ActivityDescription;
import com.kanjia.pojo.DescriptionPicture;

import java.util.List;

/**
 * liyue 2018.6.29
 */
public interface DescriptionPictureService extends BaseService<DescriptionPicture> {

    /**
     * 删除图片
     * @param activityId
     * @return
     */
    List<DescriptionPicture> delete(Integer activityId);

}