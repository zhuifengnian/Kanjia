package com.kanjia.mapper;

import com.kanjia.basic.Page;
import com.kanjia.pojo.Admin;
import com.kanjia.pojo.Enterprise;
import com.kanjia.vo.adminvo.AdminVo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminMapper extends BaseMapper<Admin>{
    /**
     * 通过open_id获得eid
     *
     * @param openId
     * @return
     */
    Integer getId(String openId);

    /**
     * 获取企业所有信息
     * @param page
     * @return
     */
    List<AdminVo> getListEnterprise(Page page);
    /**
     * 获取企业所有信息数量
     * @return
     */
    Integer getListEnterpriseCount();
}