package com.kanjia.mapper;

import com.kanjia.pojo.Enterprise;
import org.springframework.stereotype.Repository;

@Repository
public interface EnterpriseMapper extends BaseMapper<Enterprise> {
    /**
     * 通过open_id获得eid
     * @param openId
     * @return
     */
    Integer getId(String openId);
}