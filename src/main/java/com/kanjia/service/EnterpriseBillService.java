package com.kanjia.service;

import com.kanjia.basic.Page;
import com.kanjia.basic.PageInfo;
import com.kanjia.pojo.Bill;
import com.kanjia.pojo.EnterpriseBill;
import com.kanjia.vo.BillInfoVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * liyue 2018.6.29
 */
public interface EnterpriseBillService extends BaseService<com.kanjia.pojo.EnterpriseBill> {


    /**
     * 根据eid获取列表
     * @param eid
     * @return
     */
    PageInfo<List<Bill>> getListBill(@Param("eid") Integer eid, @Param("page") Page page);

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
    /**
     * 获取每月数量
     * @param eid
     * @return
     */
    List<Integer> getListBillCount( Integer eid);

}