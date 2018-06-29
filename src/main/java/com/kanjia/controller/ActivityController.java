package com.kanjia.controller;

import com.kanjia.basic.Page;
import com.kanjia.basic.PageInfo;
import com.kanjia.basic.ResponseCode;
import com.kanjia.basic.ReturnMessage;
import com.kanjia.exception.ApiException;
import com.kanjia.pojo.Activity;
import com.kanjia.pojo.User;
import com.kanjia.service.ActivityService;
import com.kanjia.service.UserService;
import com.kanjia.utils.QiNiuUtil;
import com.kanjia.wxpay.ConstantUtil;
import com.kanjia.wxpay.TenpayHttpClient;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * <br/>
 * fan 2018/6/13 11:07
 */
@RestController
@RequestMapping("/user")
public class ActivityController {


    @Autowired
    private ActivityService activityService;



//    @ApiOperation(value = "获取活动列表", notes = "获取活动列表")
//    @ResponseBody
//    @RequestMapping(value = "/getListActivity", method = RequestMethod.GET)
//    public ReturnMessage getListActivity(@RequestParam("name") String name,@RequestParam(required = false) Integer pageSize,@RequestParam(required = false) Integer pageNumber){
//        PageInfo<Activity> pageinfo = new PageInfo<Activity>();
//        pageSize=4;
//        pageinfo.setPageNum(pageNumber);
//        pageinfo.setPageSize(pageSize);
//        Page page = new Page();
//        page.setPageNumber(pageNumber);
//        page.setPageSize(pageSize);
//        if(name.equals("推荐")){
//            List<Activity> list=activityService.getAllActivity(page);
//            pageinfo.setRows( list);
//            pageinfo.setTotal(activityService.getAllActivityCount());
//        }
//        else if(name.equals("其它")){
//            List<Activity> lists=  activityService.getQitaActivity(page);
//            pageinfo.setRows( lists);
//            pageinfo.setTotal(activityService.getQitaActivityCount());
//        }
//        else if(name!=null) {
//            List<Activity>list2=activityService.getNameActivity(name,page);
//            pageinfo.setRows(list2);
//            pageinfo.setTotal(activityService.getNameActivityCount(name));
//        }
//        return new ReturnMessage(ResponseCode.OK, userInfoVO);
//    }
//
//    @ApiOperation(value = "返回用户详细信息", notes = "返回用户详细信息")
//    @ResponseBody
//    @RequestMapping(value = "/getUserInfo", method = RequestMethod.GET)
//    public ReturnMessage getUserInfo(@RequestParam("uid") Integer uid){
//        User userInfoVO = userService.selectUserInfo(uid);
//        if(userInfoVO == null){
//            throw new ApiException(ResponseCode.PARAM_EROOR, "所传uid没有数据");
//        }
//        return new ReturnMessage(ResponseCode.OK, userInfoVO);
//    }






}