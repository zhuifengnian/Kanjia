package com.hust.party.test.servicetest;

import com.kanjia.basic.PageInfo;
import com.kanjia.pojo.Enterprise;
import com.kanjia.pojo.EnterprisePayment;
import com.kanjia.pojo.User;
import com.kanjia.pojo.UserOrder;
import com.kanjia.service.*;
import com.kanjia.utils.TimeUtil;
import com.kanjia.vo.EnterpriseOrderVo;
import com.kanjia.vo.EnterprisePaymentVo;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;


import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.*;

/**
 * 专门用来做单元测试<br/>
 * fan 2018/4/26 23:17
 */


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring.xml","classpath:springmvc-config.xml", "classpath:spring-shiro.xml"})
@WebAppConfiguration    //调用javaWEB的组件，比如自动注入ServletContext Bean等等
public class ServiceTest {
    @Autowired
    private UserService userService;
    @Autowired
    private ActivityService activityService;
    @Autowired
    private EnterpriseService enterpriseService;
    @Autowired
    private UserOrderService userOrderService;
    @Autowired
    private EnterprisePaymentService enterprisePaymentService;
     @Test
    public void testUserService(){
        User record = new User();
        record.setId(55);
        List<User> select = userService.select(record, null);
        System.out.println(select);
    }
    @Test
    public void testActivity(){
      System.out.println(  activityService.getDetailsActivity(1));
    }
    @Test
    public void testEnterprise(){
        System.out.println(   activityService.getEnterpriseActivity("删除", 1,null));
    }
    @Test
    public void testEnterprises(){
        Timestamp timestamp[]=TimeUtil.getTime();
        PageInfo<EnterpriseOrderVo> pageInfo= userOrderService.getEnterpriseOrder("已取消",1,timestamp[0],timestamp[1],null);
        System.out.println(  pageInfo.getRows().get(0));
    }
    @Test
    public void insertEnterprises(){

    //  EnterprisePayment enterprisePayment=enterprisePaymentService.getEnterprisePayment(1);
//        BigDecimal totalMoney=new BigDecimal(12.2);
//        String t="21";
//       BigDecimal b= new BigDecimal(t);
//       totalMoney=totalMoney.add(b);
//       System.out.println( totalMoney);
//        EnterprisePaymentVo enterprisePaymentVo =userOrderService.getQrCode("qwert");
//        EnterprisePayment enterprisePayment  = enterprisePaymentService.getEnterprisePayment(1);
//        BigDecimal totalMoney =  enterprisePayment.getTotalMoney();
//     //   BigDecimal b=new BigDecimal(enterprisePaymentVo.getMinuPrice());
//        if(totalMoney==null){
//            totalMoney=b;
//        }
//        else{
//            totalMoney=totalMoney.add(b);
//        }
//
//        System.out.println( totalMoney);
    }
}