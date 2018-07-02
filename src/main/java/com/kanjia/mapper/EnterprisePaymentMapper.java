package com.kanjia.mapper;

import com.kanjia.pojo.EnterprisePayment;
import org.springframework.stereotype.Repository;

@Repository
public interface EnterprisePaymentMapper extends BaseMapper<EnterprisePayment> {
    /**
     * 通过entepriseId找到对象
     *
     * @param enterpriseId
     * @return
     */
    EnterprisePayment getEnterprisePayment(Integer enterpriseId);
}