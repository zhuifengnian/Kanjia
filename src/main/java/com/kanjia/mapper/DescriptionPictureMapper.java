package com.kanjia.mapper;

import com.kanjia.pojo.DescriptionPicture;
import com.kanjia.vo.ActivityDescriptionVo;
import com.kanjia.vo.DescriptionPictureVo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DescriptionPictureMapper extends BaseMapper<DescriptionPicture>{
    /**
     * 根据did获取描述图片
     * @param did
     * @return
     */
    List<String>  getDescriptionId(int did);
}