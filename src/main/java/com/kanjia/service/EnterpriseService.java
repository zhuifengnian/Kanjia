package com.kanjia.service;

import com.kanjia.pojo.Enterprise;
import com.kanjia.vo.EnterpriseVo;

/**
 * liyue 2018.6.29
 */
public interface EnterpriseService extends BaseService<Enterprise> {

    /**
     * 通过open_id获得eid
     *
     * @param openId
     * @return
     */
    Integer getId(String openId);


}