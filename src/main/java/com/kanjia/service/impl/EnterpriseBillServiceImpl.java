package com.kanjia.service.impl;

import com.kanjia.basic.Page;
import com.kanjia.basic.PageInfo;
import com.kanjia.mapper.ActivityMapper;
import com.kanjia.mapper.BaseMapper;
import com.kanjia.mapper.EnterpriseBillMapper;
import com.kanjia.mapper.UserOrderMapper;
import com.kanjia.pojo.Activity;
import com.kanjia.pojo.Enterprise;
import com.kanjia.pojo.EnterpriseBill;
import com.kanjia.pojo.UserOrder;
import com.kanjia.service.EnterpriseBillService;
import com.kanjia.vo.BillInfoVo;
import com.kanjia.vo.EnterpriseBillVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EnterpriseBillServiceImpl extends AbstractBaseServiceImpl<EnterpriseBill> implements EnterpriseBillService {
   @Autowired
   private EnterpriseBillMapper enterpriseBillMapper;
   @Autowired
   private UserOrderMapper userOrderMapper;
   @Autowired
   private ActivityMapper activityMapper;

    @Override
    public BaseMapper<EnterpriseBill> getDao() {
        return enterpriseBillMapper;
    }

    @Override
    public PageInfo<EnterpriseBillVo> getListBill(Integer eid,Page page) {
        PageInfo<EnterpriseBillVo> enterpriseBillVoPageInfo=new PageInfo<>();
        enterpriseBillVoPageInfo.setPageSize(page.getPageSize());
        enterpriseBillVoPageInfo.setPageNum(page.getPageNumber());
      List<EnterpriseBillVo> enterpriseBillVoList=  enterpriseBillMapper.getListBill(eid,page);
        enterpriseBillVoPageInfo.setRows(enterpriseBillVoList);
        enterpriseBillVoPageInfo.setTotal(enterpriseBillVoList.size());
        return enterpriseBillVoPageInfo;
    }

    @Override
    public BillInfoVo getBillInfo(Integer id) {
        EnterpriseBill enterpriseBill=enterpriseBillMapper.selectByPrimaryKey(id);
        BillInfoVo billInfoVo=new BillInfoVo();
        if(enterpriseBill.getType().equals("收入")) {
            UserOrder userOrder = userOrderMapper.selectByPrimaryKey(enterpriseBill.getOrderId());
            billInfoVo.setCreateTime(enterpriseBill.getCreateTime());
            billInfoVo.setMoney(enterpriseBill.getMoney());
            billInfoVo.setId(enterpriseBill.getId());
            billInfoVo.setOrderNumber(userOrder.getOrderNumber());
            billInfoVo.setType(enterpriseBill.getType());
            billInfoVo.setName(enterpriseBill.getTitle());
        }
        else{
            billInfoVo.setType(enterpriseBill.getType());
            billInfoVo.setMoney(enterpriseBill.getMoney());
            billInfoVo.setId(enterpriseBill.getId());
            billInfoVo.setCreateTime(enterpriseBill.getCreateTime());
            billInfoVo.setName(enterpriseBill.getTitle());
        }
        return billInfoVo;
    }

    @Override
    public List<EnterpriseBill> getMoney(Integer eid) {
        return enterpriseBillMapper.getMoney(eid);
    }


}
