package com.kanjia.service.impl;

import com.kanjia.basic.Page;
import com.kanjia.basic.PageInfo;
import com.kanjia.mapper.BaseMapper;
import com.kanjia.mapper.EnterpriseBillMapper;
import com.kanjia.mapper.UserOrderMapper;
import com.kanjia.pojo.Bill;
import com.kanjia.pojo.EnterpriseBill;
import com.kanjia.pojo.UserOrder;
import com.kanjia.service.EnterpriseBillService;
import com.kanjia.vo.BillInfoVo;
import com.kanjia.vo.EnterpriseBillVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
@Service
public class EnterpriseBillServiceImpl extends AbstractBaseServiceImpl<EnterpriseBill> implements EnterpriseBillService {
   @Autowired
   private EnterpriseBillMapper enterpriseBillMapper;
   @Autowired
   private UserOrderMapper userOrderMapper;


    @Override
    public BaseMapper<EnterpriseBill> getDao() {
        return enterpriseBillMapper;
    }

    @Override
    public PageInfo<List<Bill>> getListBill(Integer eid, Page page) {
        DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        DateFormat sdfs = new SimpleDateFormat("yyyy/MM");
        PageInfo<List<Bill>> enterpriseBillVoPageInfo=new PageInfo<>();
        if (page != null) {
            enterpriseBillVoPageInfo.setPageNum(page.getPageNumber());
            enterpriseBillVoPageInfo.setPageSize(page.getPageSize());
        }
      List<EnterpriseBillVo> enterpriseBillVoList=  enterpriseBillMapper.getListBill(eid,page);
      List<Integer> integerList=enterpriseBillMapper.getListBillCount(eid);
       List< List<Bill>> billLists=new ArrayList<>();
       int z=0;
       if(enterpriseBillVoList.size()!=0) {
           for (int i = 0; i < integerList.size(); ++i) {
               List<Bill> billList = new ArrayList<>();
               for (int j = 0; j < integerList.get(i); ++j) {
                   Bill bill = new Bill();
                   Bill.Content content = bill.new Content();//把内部类当成一个成员变量进行实例化
                   content.setId(enterpriseBillVoList.get(z).getId());
                   content.setCreateTime(sdf.format(enterpriseBillVoList.get(z).getCreateTime()));
                    if(enterpriseBillVoList.get(z).getType().equals("收入")){
                       content.setMoney(enterpriseBillVoList.get(z).getMoney().toString());
                   }else{
                        content.setMoney("-"+enterpriseBillVoList.get(z).getMoney().toString());
                    }

                   content.setTitle(enterpriseBillVoList.get(z).getTitle());
                   content.setType(enterpriseBillVoList.get(z).getType());
                   content.setBillNumber(enterpriseBillVoList.get(z).getBillNumber());
                   bill.setContent(content);
                   bill.setTime(sdfs.format(enterpriseBillVoList.get(z).getCreateTime()));

                   billList.add(bill);
                   z++;
               }
               billLists.add(billList);
           }
       }
        enterpriseBillVoPageInfo.setRows(billLists);
        enterpriseBillVoPageInfo.setTotal(enterpriseBillVoList.size());
        return enterpriseBillVoPageInfo;
    }

    @Override
    public BillInfoVo getBillInfo(Integer id) {
        com.kanjia.pojo.EnterpriseBill enterpriseBill=enterpriseBillMapper.selectByPrimaryKey(id);
        BillInfoVo billInfoVo=new BillInfoVo();
        UserOrder userOrder = userOrderMapper.selectByPrimaryKey(enterpriseBill.getOrderId());
        if(enterpriseBill.getType().equals("收入")) {
            billInfoVo.setCreateTime(enterpriseBill.getCreateTime());
            billInfoVo.setMoney(enterpriseBill.getMoney().toString());
            billInfoVo.setId(enterpriseBill.getId());
            billInfoVo.setOrderNumber(userOrder.getOrderNumber());
            billInfoVo.setType(enterpriseBill.getType());
            billInfoVo.setName(enterpriseBill.getTitle());
            billInfoVo.setBillNumber(enterpriseBill.getBillNumber());
        }
        else{
            billInfoVo.setType(enterpriseBill.getType());
            billInfoVo.setMoney("-"+enterpriseBill.getMoney().toString());
            billInfoVo.setId(enterpriseBill.getId());
            billInfoVo.setCreateTime(enterpriseBill.getCreateTime());
            billInfoVo.setName(enterpriseBill.getTitle());
            billInfoVo.setBillNumber(enterpriseBill.getBillNumber());
            billInfoVo.setOrderNumber(userOrder.getOrderNumber());
        }
        return billInfoVo;
    }

    @Override
    public List<EnterpriseBill> getMoney(Integer eid) {
        return enterpriseBillMapper.getMoney(eid);
    }

    @Override
    public List<Integer> getListBillCount(Integer eid) {
        return enterpriseBillMapper.getListBillCount(eid);
    }


}
