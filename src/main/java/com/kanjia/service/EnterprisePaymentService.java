package com.kanjia.service;

import com.kanjia.pojo.Enterprise;
import com.kanjia.pojo.EnterprisePayment;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * liyue 2018.6.29
 */
public interface EnterprisePaymentService extends BaseService<EnterprisePayment>{

    /**
     * 通过entepriseId找到对象
     * @param enterpriseId
     * @return
     */
    EnterprisePayment getEnterprisePayment(Integer enterpriseId);
}