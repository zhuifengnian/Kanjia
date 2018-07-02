package com.kanjia.mapper;

import com.kanjia.pojo.Picture;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PictureMapper extends BaseMapper<Picture> {
    /**
     * 通过activity_id获取picture列表
     *
     * @param activityId
     * @return
     */
    List<String> getPicture(Integer activityId);
}