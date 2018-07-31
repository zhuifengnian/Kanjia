package com.kanjia.mapper;

import com.kanjia.pojo.Enterprise;
import com.kanjia.vo.EnterpriseVo;
import org.springframework.stereotype.Repository;

@Repository
public interface EnterpriseMapper extends BaseMapper<Enterprise> {
    /**
     * 通过open_id获得eid
     *
     * @param openId
     * @return
     */
    Integer getId(String openId);

    /**
     * 根据aid提取信息
     * @param aid
     * @return
     */
    EnterpriseVo getEnterpriseId(Integer aid);
}