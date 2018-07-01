package com.kanjia.service.impl;

import com.kanjia.mapper.BaseMapper;
import com.kanjia.mapper.EnterprisePaymentMapper;
import com.kanjia.mapper.UserOrderMapper;
import com.kanjia.pojo.EnterprisePayment;
import com.kanjia.pojo.UserOrder;
import com.kanjia.service.EnterprisePaymentService;
import com.kanjia.service.UserOrderService;
import com.kanjia.vo.EnterprisePaymentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

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
