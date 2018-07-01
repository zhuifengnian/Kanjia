package com.kanjia.service;

import com.kanjia.basic.Page;
import com.kanjia.basic.PageInfo;
import com.kanjia.pojo.Activity;
import com.kanjia.pojo.Picture;
import com.kanjia.vo.DetailActivityVo;
import com.kanjia.vo.PageActivityVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * liyue 2018.6.29
 */
public interface PictureService extends BaseService<Picture>{

    /**
     * 通过activity_id获取picture列表
     * @param activityId
     * @return
     */
    List<String> getPicture(Integer activityId);

}