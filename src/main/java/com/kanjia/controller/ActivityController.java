package com.kanjia.controller;

import com.kanjia.basic.PageInfo;
import com.kanjia.basic.ResponseCode;
import com.kanjia.basic.ReturnMessage;
import com.kanjia.service.ActivityService;
import com.kanjia.utils.PageUtil;
import com.kanjia.vo.DetailActivityVo;
import com.kanjia.vo.PageActivityVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * liyue 2018/6/29
 */
@RestController
@RequestMapping("/activity")
public class ActivityController {


    @Autowired
    private ActivityService activityService;


    //    @ApiOperation(value = "获取活动列表", notes = "获取活动列表")
//    @ResponseBody
//    @RequestMapping(value = "/getListActivity", method = RequestMethod.POST)
//    public ReturnMessage getListActivity(@RequestParam("name") String name,@RequestParam(required = false) Integer pageSize,@RequestParam(required = false) Integer pageNumber){
//       PageInfo<PageActivityVo> pageinfo =new PageInfo<>();
//       pageinfo.setTotal(activityService.getAllActivityCount());
//       pageinfo.setRows(activityService.getAllActivity(PageUtil.setPage(pageNumber)));
//        return new ReturnMessage(ResponseCode.OK, pageinfo);
//    }
    @ApiOperation(value = "获取活动列表", notes = "获取活动列表")
    @ResponseBody
    @RequestMapping(value = "/getListActivity", method = RequestMethod.POST)
    public ReturnMessage getListActivity(@RequestParam(required = false) Integer pageSize, @RequestParam(required = false) Integer pageNumber) {
        PageInfo<PageActivityVo> pageinfo = activityService.getAllActivity(PageUtil.setPage(pageNumber));

        return new ReturnMessage(ResponseCode.OK, pageinfo);
    }

    @ApiOperation(value = "获取活动详情", notes = "获取活动详情")
    @ResponseBody
    @RequestMapping(value = "/getIdActivity", method = RequestMethod.POST)
    public ReturnMessage getIdActivity(@RequestParam("id") Integer id) {

        DetailActivityVo detailActivityVo = activityService.getDetailsActivity(id);
        return new ReturnMessage(ResponseCode.OK, detailActivityVo);
    }

}