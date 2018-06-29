package com.kanjia.service.impl;

import com.kanjia.mapper.ActivityMapper;
import com.kanjia.mapper.BaseMapper;
import com.kanjia.pojo.Activity;
import com.kanjia.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActivityServiceImpl extends AbstractBaseServiceImpl<Activity> implements ActivityService {
    @Autowired
    private ActivityMapper activityMapper;
    @Override
    public BaseMapper<Activity> getDao() {
        return activityMapper;
    }
}
