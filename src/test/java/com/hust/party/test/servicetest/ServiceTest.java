package com.hust.party.test.servicetest;

import com.kanjia.basic.Const;
import com.kanjia.basic.PageInfo;
import com.kanjia.mapper.ActivityMapper;
import com.kanjia.mapper.HelpUserMapper;
import com.kanjia.pojo.Activity;
import com.kanjia.pojo.ActivityDescription;
import com.kanjia.pojo.EnterpriseBill;
import com.kanjia.pojo.UserOrder;
import com.kanjia.service.*;
import com.kanjia.utils.JsonUtil;
import com.kanjia.utils.OverTimeUtil;
import com.kanjia.utils.TimeUtil;
import com.kanjia.vo.*;
import com.kanjia.vo.ordervo.OrderDetailVO2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;

/**
 * 专门用来做单元测试<br/>
 * fan 2018/4/26 23:17
 */


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring.xml", "classpath:springmvc-config.xml", "classpath:spring-shiro.xml"})
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
    @Autowired
    private HelpUserMapper helpUserMapper;
    @Autowired
    private ActivityMapper activityMapper;
    @Autowired
    private EnterpriseBillService enterpriseBillService;

    @Test
    public void testUserService() {
//        User record = new User();
//        record.setId(55);
//        List<User> select = userService.select(record, null);
//        System.out.println(select);
        System.out.println(userOrderService.getOrdersPicture(1));
    }

    @Test
    public void testActivity() {
        System.out.println(activityService.getDetailsActivity(2));
    }

    @Test
    public void testEnterprise() {
      //  System.out.println(activityService.getEnterpriseActivity("删除", 1, null));
    }

    @Test
    public void testEnterprises() {
        Timestamp timestamp[] = TimeUtil.getTime();
        PageInfo<EnterpriseOrderVo> pageInfo = userOrderService.getEnterpriseOrder("已取消", 1, timestamp[0], timestamp[1], null);
        System.out.println(pageInfo.getRows().get(0));
    }

    @Test
    public void insertEnterprises() {
        System.out.println(userOrderService.EnterpriseMonthOrder(1, OverTimeUtil.getTime("一个月"), null));
    }

    @Test
    public void listKanjiaOrders(){
        PageInfo<KanjiaOrderVo> kanjiaOrderVoPageInfo = userOrderService.listKanjiaOrders(1, null);
        System.out.println(kanjiaOrderVoPageInfo);
    }

    @Test
    public void testGetHelperAvatar(){
        List<OrderHelperVO> orderHelpers = helpUserMapper.getOrderHelpers(1, null);
        System.out.println(orderHelpers);
    }

    @Test
    public void testGetOrderDetail(){
        OrderDetailVO orderDetail = userOrderService.getOrderDetail(1);
        System.out.println(orderDetail);
    }

    @Test
    public void testListOrders(){
        PageInfo<OrderListVO> orderListVOPageInfo = userOrderService.listOrders(1, Const.ORDER_LIST_STATUS_DRAWBACKING, null);
        System.out.println(orderListVOPageInfo);

    }
    @Test
    public void activity(){
//        Activity activity=new Activity();
//        activity.setState(4);
//        activity.setId(5);
//        Integer insert = activityService.updateByPrimaryKeySelective(activity);
     //   List<EnterpriseBill> enterpriseBill=enterpriseBillService.getMoney(1);
     //   System.out.println(enterpriseBill.size());
        String obj="{\"aid\":1,\"detail\":[{\"title\":\"注意事项\",\"content\":\"不超过3天\"},{\"title\":\"你好\",\"content\":\"不超过1天\"}]}";

       List<ActivityDescription>  activityDescriptionList=JsonUtil.checkUserIdJson(obj);
       System.out.println(activityDescriptionList.size());
    //  List<PageActivityVo> pageActivityVos=  activityMapper.getAllActivity(1,null);
    //  System.out.println(pageActivityVos.size());
    }

    @Test
    public void userOrderService2(){
        OrderDetailVO2 orderDetail2 = userOrderService.getOrderDetail2(29);
        System.out.println(orderDetail2);
    }

}