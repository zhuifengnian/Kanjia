package com.kanjia.service.impl;

import com.kanjia.mapper.BaseMapper;
import com.kanjia.mapper.PictureMapper;
import com.kanjia.pojo.Picture;
import com.kanjia.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PictureServiceImpl extends AbstractBaseServiceImpl<Picture> implements PictureService {
    @Autowired
    private PictureMapper pictureMapper;

    @Override
    public List<String> getPicture(Integer activityId) {
        return pictureMapper.getPicture(activityId);
    }

    @Override
    public BaseMapper<Picture> getDao() {
        return pictureMapper;
    }
}
