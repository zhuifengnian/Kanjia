package com.kanjia.service;

import com.kanjia.basic.Page;
import com.kanjia.basic.PageInfo;
import com.kanjia.pojo.Enterprise;
import com.kanjia.pojo.EnterpriseBill;
import com.kanjia.vo.BillInfoVo;
import com.kanjia.vo.EnterpriseBillVo;
import com.kanjia.vo.PageEnterpriseVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * liyue 2018.6.29
 */
public interface EnterpriseBillService extends BaseService<EnterpriseBill> {


    /**
     * 根据eid获取列表
     * @param eid
     * @return
     */
    PageInfo<EnterpriseBillVo> getListBill(@Param("eid") Integer eid, @Param("page") Page page);

    /**
     * 账单详情
     * @param id
     * @return
     */
    BillInfoVo getBillInfo(Integer id);
    /**
     * 获取当月钱数
     * @return
     */
    List<EnterpriseBill> getMoney(Integer eid);

}