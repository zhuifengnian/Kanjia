package com.kanjia.service.impl;

import com.kanjia.mapper.ActivityDescriptionMapper;
import com.kanjia.mapper.BaseMapper;
import com.kanjia.pojo.ActivityDescription;
import com.kanjia.service.ActivityDescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActivityDescriptionServiceImpl extends AbstractBaseServiceImpl<ActivityDescription> implements ActivityDescriptionService {
    @Autowired
    private ActivityDescriptionMapper activityDescriptionMapper;

    @Override
    public BaseMapper<ActivityDescription> getDao() {
        return activityDescriptionMapper;
    }
}
