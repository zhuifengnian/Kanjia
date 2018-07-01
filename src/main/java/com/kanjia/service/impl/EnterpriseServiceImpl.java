package com.kanjia.service.impl;


import com.kanjia.mapper.BaseMapper;
import com.kanjia.mapper.EnterpriseMapper;
import com.kanjia.mapper.EnterprisePaymentMapper;
import com.kanjia.pojo.Enterprise;
import com.kanjia.pojo.EnterprisePayment;
import com.kanjia.service.EnterpriseService;
import com.kanjia.utils.QiNiuUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class EnterpriseServiceImpl  extends AbstractBaseServiceImpl<Enterprise> implements EnterpriseService{
   @Autowired
   private EnterpriseMapper enterpriseMapper;
   @Autowired
   private EnterprisePaymentMapper enterprisePaymentMapper;
   @Autowired
   private EnterpriseService enterpriseService;
    @Override
    public BaseMapper<Enterprise> getDao() {
        return enterpriseMapper;
    }

    @Override
    public Integer getId(String openId) {

        return enterpriseMapper.getId(openId);
    }



}
