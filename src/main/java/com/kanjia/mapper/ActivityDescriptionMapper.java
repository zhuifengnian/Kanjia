package com.kanjia.mapper;

import com.kanjia.pojo.ActivityDescription;
import com.kanjia.vo.ActivityDescriptionVo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ActivityDescriptionMapper  extends BaseMapper<ActivityDescription>{
    /**
     * 根据aid获取描述
     * @param aid
     * @return
     */
    List<ActivityDescriptionVo> getActivityId(int aid);
}