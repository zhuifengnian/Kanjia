package com.kanjia.service.impl;

import com.kanjia.mapper.BaseMapper;
import com.kanjia.mapper.EnterprisePaymentMapper;
import com.kanjia.pojo.EnterprisePayment;
import com.kanjia.service.EnterprisePaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnterprisePaymentServiceImpl extends AbstractBaseServiceImpl<EnterprisePayment> implements EnterprisePaymentService {
    @Autowired
    private EnterprisePaymentMapper enterprisePaymentMapper;

    public BaseMapper<EnterprisePayment> getDao() {
        return enterprisePaymentMapper;
    }


    @Override
    public EnterprisePayment getEnterprisePayment(Integer enterpriseId) {
        return enterprisePaymentMapper.getEnterprisePayment(enterpriseId);
    }
}
