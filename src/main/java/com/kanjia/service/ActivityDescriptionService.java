package com.kanjia.service;

import com.kanjia.pojo.ActivityDescription;

/**
 * liyue 2018.6.29
 */
public interface ActivityDescriptionService extends BaseService<ActivityDescription> {


    /**
     * 删除数据库中数据
     * @param activityId
     * @return
     */
    Integer delete(Integer activityId);
}