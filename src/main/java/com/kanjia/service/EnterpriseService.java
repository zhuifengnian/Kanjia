package com.kanjia.service;

import com.kanjia.basic.Page;
import com.kanjia.basic.PageInfo;
import com.kanjia.pojo.Activity;
import com.kanjia.pojo.Enterprise;
import com.kanjia.vo.PageActivityVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 *
 * liyue 2018.6.29
 */
public interface EnterpriseService extends BaseService<Enterprise>{

    /**
     * 通过open_id获得eid
     * @param openId
     * @return
     */
    Integer getId(String openId);


}