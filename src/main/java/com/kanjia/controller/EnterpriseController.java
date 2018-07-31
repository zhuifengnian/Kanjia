package com.kanjia.controller;

import com.kanjia.basic.PageInfo;
import com.kanjia.basic.ResponseCode;
import com.kanjia.basic.ReturnMessage;
import com.kanjia.pojo.Activity;
import com.kanjia.pojo.Enterprise;
import com.kanjia.pojo.EnterprisePayment;
import com.kanjia.service.ActivityService;
import com.kanjia.service.EnterprisePaymentService;
import com.kanjia.service.EnterpriseService;
import com.kanjia.service.UserOrderService;
import com.kanjia.utils.OverTimeUtil;
import com.kanjia.utils.PageUtil;
import com.kanjia.utils.QiNiuUtil;
import com.kanjia.utils.TimeUtil;
import com.kanjia.vo.EnterpriseOrderPriceVo;
import com.kanjia.vo.EnterpriseOrderVo;
import com.kanjia.vo.PageActivityVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.util.Calendar;

/**
 * liyue 2018/6/29
 */
@RestController
@RequestMapping("/enterprise")
public class EnterpriseController {


    @Autowired
    private EnterpriseService enterpriseService;
    @Autowired
    private ActivityService activityService;
    @Autowired
    private UserOrderService userOrderService;
    @Autowired
    private EnterprisePaymentService enterprisePaymentService;

    @ApiOperation(value = "添加活动", notes = "添加活动")
    @ResponseBody
    @RequestMapping(value = "/insertActivity", method = RequestMethod.POST)
    public ReturnMessage insertActivity(Activity activity, @RequestParam("enterpriseId") Integer enterpriseId) {
        activity.setEnterpriseId(enterpriseId);
        activity.setState(1);
        Integer insert = activityService.insert(activity);
        return new ReturnMessage(ResponseCode.OK, insert);
    }
    @RequestMapping(value = "/enterprise/insertActivityPicture", method = RequestMethod.POST)
    @ApiOperation(value = "存储图片信息")
    @ResponseBody
    public ReturnMessage insertActivityPicture(@RequestParam("activityId") Integer activityId, @RequestParam(value = "flyfile", required = false) MultipartFile flfile, Integer num) {
        String picture = "";
        Activity activity = new Activity();
        activity.setId(activityId);
        if (flfile != null)
            picture = QiNiuUtil.manageFile(flfile);
        if (num == 1) {
            activity.setPicture(picture);
        } else if (num == 2) {
            activity.setVideo(picture);
        }

        int insert = activityService.updateByPrimaryKeySelective(activity);

        return new ReturnMessage(ResponseCode.OK, insert);
    }


    @ApiOperation(value = "修改活动", notes = "修改活动")
    @ResponseBody
    @RequestMapping(value = "/updateActivity", method = RequestMethod.POST)
    public ReturnMessage updateActivity(Activity activity, @RequestParam("id") Integer id) {

        activity.setId(id);
        activity.setUpdateTime(Calendar.getInstance().getTime());
        Integer insert = activityService.updateByPrimaryKeySelective(activity);
        return new ReturnMessage(ResponseCode.OK, insert);
    }


    @ApiOperation(value = "删除活动", notes = "删除活动")
    @ResponseBody
    @RequestMapping(value = "/deleteActivity", method = RequestMethod.POST)
    public ReturnMessage deleteActivity(Integer id) {
        Activity activity = new Activity();
        activity.setUpdateTime(Calendar.getInstance().getTime());
        activity.setState(0);
        Integer insert = activityService.updateByPrimaryKeySelective(activity);
        return new ReturnMessage(ResponseCode.OK, insert);
    }

    @ApiOperation(value = "查看活动", notes = "查看活动")
    @ResponseBody
    @RequestMapping(value = "/checkActivity", method = RequestMethod.POST)
    public ReturnMessage checkActivity(@RequestParam("id") Integer id, @RequestParam("name") String name, @RequestParam(required = false) Integer pageSize, @RequestParam(required = false) Integer pageNumber) {

        PageInfo<PageActivityVo> pageInfo = activityService.getEnterpriseActivity(name, id, PageUtil.setPage(pageNumber));
        return new ReturnMessage(ResponseCode.OK, pageInfo);
    }

    @ApiOperation(value = "查看订单", notes = "查看订单")
    @ResponseBody
    @RequestMapping(value = "/checkOrder", method = RequestMethod.POST)
    public ReturnMessage checkOrder(@RequestParam("id") Integer id, @RequestParam("name") String name, @RequestParam(required = false) Integer pageSize, @RequestParam(required = false) Integer pageNumber) {
        Timestamp timestamp[] = TimeUtil.getTime();
        PageInfo<EnterpriseOrderVo> pageInfo = userOrderService.getEnterpriseOrder(name, id, timestamp[0], timestamp[1], PageUtil.setPage(pageNumber));
        return new ReturnMessage(ResponseCode.OK, pageInfo);
    }

    @RequestMapping(value = "/insertEnterprise", method = RequestMethod.POST)
    @ApiOperation(value = "企业注册接口", httpMethod = "POST")
    @ResponseBody
    public ReturnMessage insertEnterprise(Enterprise enterprise) {
        Integer id = enterpriseService.getId(enterprise.getOpenId());
        Integer insert = null;
        if (null == id) {
            enterprise.setState(1);
            insert = enterpriseService.insert(enterprise);
            if (insert != null) {
                EnterprisePayment enterprisePayment = new EnterprisePayment();
                enterprisePayment.setEnterpriseId(insert);

                insert = enterprisePaymentService.insert(enterprisePayment);
            }
        }


        return new ReturnMessage(ResponseCode.OK, insert);
    }

    @RequestMapping(value = "/insertEnterpriseLicence", method = RequestMethod.POST)
    @ApiOperation(value = "企业上传营业执照接口", httpMethod = "POST")
    @ResponseBody
    public ReturnMessage insertEnterpriseLicence(@RequestParam("enterpriseId") Integer enterpriseId, @RequestParam(value = "flyfile", required = false) MultipartFile flfile) {
        String picture = "";
        Enterprise enterprise = new Enterprise();
        enterprise.setId(enterpriseId);
        if (null != flfile) {
            picture = QiNiuUtil.manageFile(flfile);
        }
        enterprise.setLicense(picture);
        enterprise.setUpdateTime(Calendar.getInstance().getTime());
        int insert = enterpriseService.updateByPrimaryKeySelective(enterprise);
        return new ReturnMessage(ResponseCode.OK, insert);
    }

    @RequestMapping(value = "/checkData", method = RequestMethod.POST)
    @ApiOperation(value = "企业获取数据", httpMethod = "POST")
    @ResponseBody
    public ReturnMessage checkData(@RequestParam("enterpriseId") Integer enterpriseId, @RequestParam("name") String name, @RequestParam(required = false) Integer pageSize, @RequestParam(required = false) Integer pageNumber) {

        PageInfo<EnterpriseOrderVo> pageInfo = userOrderService.EnterpriseMonthOrder(enterpriseId, OverTimeUtil.getTime(name), PageUtil.setPage(pageNumber));


        return new ReturnMessage(ResponseCode.OK, pageInfo);
    }
}