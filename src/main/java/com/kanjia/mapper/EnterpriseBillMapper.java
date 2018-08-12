package com.kanjia.mapper;

import com.kanjia.basic.Page;
import com.kanjia.pojo.EnterpriseBill;
import com.kanjia.vo.EnterpriseBillVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnterpriseBillMapper extends BaseMapper<com.kanjia.pojo.EnterpriseBill> {
    /**
     * 根据eid获取列表
     * @param eid
     * @return
     */
    List<EnterpriseBillVo> getListBill(@Param("eid") Integer eid, @Param("page") Page page);

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