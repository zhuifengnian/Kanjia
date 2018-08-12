package com.kanjia.service.impl;


import com.kanjia.mapper.BaseMapper;
import com.kanjia.mapper.EnterpriseMapper;
import com.kanjia.mapper.EnterprisePaymentMapper;
import com.kanjia.pojo.ActivityJian;
import com.kanjia.pojo.Enterprise;
import com.kanjia.service.EnterpriseService;
import com.kanjia.vo.PageEnterpriseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnterpriseServiceImpl extends AbstractBaseServiceImpl<Enterprise> implements EnterpriseService {
    @Autowired
    private EnterpriseMapper enterpriseMapper;

    @Override
    public BaseMapper<Enterprise> getDao() {
        return enterpriseMapper;
    }

    @Override
    public Integer getId(String openId) {

        return enterpriseMapper.getId(openId);
    }

    @Override
    public PageEnterpriseVo getEnterpriseinfo(Integer eid) {
        return enterpriseMapper.getEnterpriseinfo(eid);
    }

    @Override
    public ActivityJian getActivityJian(Integer activity_id) {
        return enterpriseMapper.getActivityJian(activity_id);
    }


}
