package com.kanjia.service.impl;

import com.kanjia.mapper.BaseMapper;
import com.kanjia.mapper.DescriptionPictureMapper;
import com.kanjia.pojo.DescriptionPicture;
import com.kanjia.service.DescriptionPictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DescriptionPictureServiceImpl extends AbstractBaseServiceImpl<DescriptionPicture> implements DescriptionPictureService  {
   @Autowired
   private DescriptionPictureMapper descriptionPictureMapper;
    @Override
    public BaseMapper<DescriptionPicture> getDao() {
        return descriptionPictureMapper;
    }
}
