package com.kanjia.controller;

import com.kanjia.basic.Const;
import com.kanjia.basic.PageInfo;
import com.kanjia.basic.ResponseCode;
import com.kanjia.basic.ReturnMessage;
import com.kanjia.exception.ApiException;
import com.kanjia.pojo.*;
import com.kanjia.service.ActivityService;
import com.kanjia.service.EnterprisePaymentService;
import com.kanjia.service.EnterpriseService;
import com.kanjia.service.UserOrderService;
import com.kanjia.utils.PageUtil;
import com.kanjia.utils.QiNiuUtil;
import com.kanjia.utils.TimeUtil;
import com.kanjia.vo.*;
import com.kanjia.wxpay.ConstantUtil;
import com.kanjia.wxpay.TenpayHttpClient;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
          Integer  insert = activityService.insert(activity);

        return new ReturnMessage(ResponseCode.OK, insert);
    }
    @ApiOperation(value = "活动下架", notes = "活动下架")
    @ResponseBody
    @RequestMapping(value = "/undercarriageActivity", method = RequestMethod.POST)
    public ReturnMessage undercarriageActivity(@RequestParam("activityId") Integer activityId) {
       Activity activity=new Activity();
        activity.setState(Const.ACTIVITY_TYPE_down);
        activity.setId(activityId);
        Integer insert = activityService.updateByPrimaryKeySelective(activity);
        return new ReturnMessage(ResponseCode.OK, insert);
    }
    @ApiOperation(value = "活动上架", notes = "活动上架")
    @ResponseBody
    @RequestMapping(value = "/groundActivity", method = RequestMethod.POST)
    public ReturnMessage groundActivity(@RequestParam("activityId") Integer activityId) {
        Activity activity=new Activity();
        activity.setState(Const.ACTIVITY_TYPE_GROUP);
        activity.setId(activityId);
        Integer insert = activityService.updateByPrimaryKeySelective(activity);
        return new ReturnMessage(ResponseCode.OK, insert);
    }
    @RequestMapping(value = "/insertActivityPicture", method = RequestMethod.POST)
    @ApiOperation(value = "存储活动图片信息")
    @ResponseBody
    public ReturnMessage insertActivityPicture(@RequestParam("activityId") Integer activityId, @RequestParam(value = "picture", required = false) MultipartFile flfile) {
        String picture = "";
        Activity activity = new Activity();
        activity.setId(activityId);
        if (flfile != null) {
            picture = QiNiuUtil.manageFile(flfile);
            activity.setPicture(picture);
        }
        int insert = activityService.updateByPrimaryKeySelective(activity);

        return new ReturnMessage(ResponseCode.OK, insert);
    }

    @RequestMapping(value = "/insertEnterprisePicture", method = RequestMethod.POST)
    @ApiOperation(value = "存储企业头像图片信息")
    @ResponseBody
    public ReturnMessage insertEnterprisePicture(@RequestParam("enterpriseId") Integer enterpriseId, @RequestParam(value = "picture", required = false) MultipartFile flfile) {
        String picture = "";
        Enterprise enterprise=new Enterprise();
        enterprise.setId(enterpriseId);
        if (flfile != null) {
            picture = QiNiuUtil.manageFile(flfile);
           enterprise.setAvatarurl(picture);
        }
        int insert = enterpriseService.updateByPrimaryKeySelective(enterprise);

        return new ReturnMessage(ResponseCode.OK, insert);
    }
    @ApiOperation(value = "修改活动返回", notes = "修改活动返回")
    @ResponseBody
    @RequestMapping(value = "/modifyActivityInfo", method = RequestMethod.POST)
    public ReturnMessage modifyActivityInfo(Activity activity) {

        activity.setUpdateTime(Calendar.getInstance().getTime());
        Integer insert = activityService.updateByPrimaryKeySelective(activity);
        Activity activity1=activityService.selectByPrimaryKey(insert);
        return new ReturnMessage(ResponseCode.OK, activity1);
    }

    @ApiOperation(value = "删除活动", notes = "删除活动")
    @ResponseBody
    @RequestMapping(value = "/deleteActivity", method = RequestMethod.POST)
    public ReturnMessage deleteActivity(@RequestParam("activity_id") Integer id) {
        Activity activity = new Activity();
        activity.setUpdateTime(Calendar.getInstance().getTime());
        activity.setState(0);
        activity.setId(id);
        Integer insert = activityService.updateByPrimaryKeySelective(activity);
        return new ReturnMessage(ResponseCode.OK, insert);
    }

    @ApiOperation(value = "查看活动", notes = "查看活动")
    @ResponseBody
    @RequestMapping(value = "/checkActivity", method = RequestMethod.POST)
    public ReturnMessage checkActivity(@RequestParam("enterprise_id") Integer id, @RequestParam(required = false) Integer pageSize, @RequestParam(required = false) Integer pageNumber) {

        PageInfo<PageActivityVo> pageInfo = activityService.getEnterpriseActivity( id, PageUtil.setPage(pageSize, pageNumber));
        return new ReturnMessage(ResponseCode.OK, pageInfo);
    }

    @ApiOperation(value = "查看订单", notes = "查看订单")
    @ResponseBody
    @RequestMapping(value = "/checkOrder", method = RequestMethod.POST)
    public ReturnMessage checkOrder(@RequestParam("enterprise_id") Integer id, @RequestParam("name") String name, @RequestParam(required = false) Integer pageSize, @RequestParam(required = false) Integer pageNumber) {
        Timestamp timestamp[] = TimeUtil.getTime();
        PageInfo<EnterpriseOrderVo> pageInfo = userOrderService.getEnterpriseOrder(name, id, timestamp[0], timestamp[1], PageUtil.setPage(pageSize, pageNumber));
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

    @ApiOperation(value = "企业注册或登录", notes = "插入企业到数据库，当企业已经存在时，不插入，会返回商家信息")
    @ResponseBody
    @RequestMapping(value = "/insertAndLogin", method = RequestMethod.POST)
    public ReturnMessage insertAndLogin(Enterprise enterprise) {
        //当商家不存在时，在数据库中先记录这个；否则直接返回商家
        //在数据库中根据open_id查找用户是否存在
        Integer enid = enterpriseService.getId(enterprise.getOpenId());
        if (enid == null) {
            //执行插入操作
            enterprise.setState(1);
            enid = enterpriseService.insert(enterprise);
            if(enid != null){
                //商户账户
                EnterprisePayment enterprisePayment = new EnterprisePayment();
                enterprisePayment.setEnterpriseId(enid);

                enterprisePaymentService.insert(enterprisePayment);
            }
        }
        Enterprise enterprise1 = enterpriseService.selectByPrimaryKey(enid);
        return new ReturnMessage(ResponseCode.OK, enterprise1);
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

    @RequestMapping(value = "/getEnterpriseinfo", method = RequestMethod.POST)
    @ApiOperation(value = "企业获取数据", httpMethod = "POST")
    @ResponseBody
    public ReturnMessage getEnterpriseinfo(@RequestParam("enterpriseId") Integer enterpriseId) {

        PageEnterpriseVo pageEnterpriseVo=enterpriseService.getEnterpriseinfo(enterpriseId);

        return new ReturnMessage(ResponseCode.OK, pageEnterpriseVo);
    }
    @RequestMapping(value = "/modifyEnterpriseinfo", method = RequestMethod.POST)
    @ApiOperation(value = "修改企业信息", httpMethod = "POST")
    @ResponseBody
    public ReturnMessage modifyEnterpriseinfo(Enterprise enterprise) {
        int insert=enterpriseService.updateByPrimaryKeySelective(enterprise);

        return new ReturnMessage(ResponseCode.OK, insert);
    }
    @RequestMapping(value = "/collectmoney", method = RequestMethod.POST)
    @ApiOperation(value = "点击确认收钱", httpMethod = "POST")
    @ResponseBody
    public ReturnMessage collectmoney(@RequestParam("order_number") String order_number){
       int []insert=userOrderService.getCurrentPrice(order_number);
       return new ReturnMessage(ResponseCode.OK, insert);
    }
    @RequestMapping(value = "/scancode", method = RequestMethod.POST)
    @ApiOperation(value = "扫码返回信息", httpMethod = "POST")
    @ResponseBody
    public ReturnMessage scancode(@RequestParam("qr_code") String qr_code){
        OrderInfoVo orderInfo =userOrderService.getOrderInfo(qr_code);
        return new ReturnMessage(ResponseCode.OK, orderInfo);
    }
    @ApiOperation(value = "获取活动详情", notes = "获取活动详情")
    @ResponseBody
    @RequestMapping(value = "/getIdActivity", method = RequestMethod.POST)
    public ReturnMessage getIdActivity(@RequestParam("activity_id") Integer id) {

        ActivityJian activityJian=enterpriseService.getActivityJian(id);
        return new ReturnMessage(ResponseCode.OK, activityJian);
    }


    @ApiOperation(value = "返回商户端用户的openid", notes = "获取商户端用户的openid，这里是通过服务器向微信的服务器发送请求获得的，需要传入js_code")
    @ResponseBody
    @RequestMapping(value = "/getopenid", method = RequestMethod.POST)
    public ReturnMessage getopenid(@RequestParam("js_code") String js_code) {

        TenpayHttpClient httpClient = new TenpayHttpClient();
        String tmpUrl = ConstantUtil.GET_OPENID_URL + "?appid=" + ConstantUtil.APP_ID2 + "&secret=" + ConstantUtil.APP_SECRET2 + "&js_code=" + js_code + "&grant_type=authorization_code";
        try {
            httpClient.httpGetMethod(tmpUrl);
            String resContent = httpClient.getResContent();
            String []ret=new String[2];
            ret[0]=resContent;
          Integer insert= enterpriseService.getId(resContent);
          if(insert!=null){
              ret[1]="2";
          }
          else{
              ret[2]="1";
          }
            return new ReturnMessage(ResponseCode.OK, ret);
        } catch (IOException e) {
            throw new ApiException(ResponseCode.NETWORK_ERROR, "网络请求出现错误");
        }
    }
}