package com.kanjia.controller;

import com.kanjia.basic.ResponseCode;
import com.kanjia.basic.ReturnMessage;
import com.kanjia.service.PintuanService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 拼团的controller<br/>
 * fan 2018/8/21 15:01
 */
@Controller
@RequestMapping("/pintuan")
public class PintuanController {
    @Autowired
    private PintuanService pintuanService;

    @ApiOperation(value = "获取拼团id", notes = "根据订单获取拼团的id")
    @ResponseBody
    @RequestMapping(value = "/getPintuanId", method = RequestMethod.POST)
    public ReturnMessage getPintuanId(@RequestParam("oid") Integer oid) {
        try {
            return pintuanService.getPintuanId(oid);
        } catch (Exception e) {
            return new ReturnMessage(ResponseCode.SERVER_ERROR, "服务器内部错误");
        }
    }

    @ApiOperation(value = "获取拼团id", notes = "根据订单获取拼团的id")
    @ResponseBody
    @RequestMapping(value = "/getPintuanInfo", method = RequestMethod.POST)
    public ReturnMessage getPintuanInfo(@RequestParam("tuanid") Integer tuanid) {
        try {
            return pintuanService.getPintuanInfo(tuanid);
        } catch (Exception e) {
            return new ReturnMessage(ResponseCode.SERVER_ERROR, "服务器内部错误");
        }
    }

    @ApiOperation(value = "参团接口", notes = "根据团id和用户id来参团")
    @ResponseBody
    @RequestMapping(value = "/engageTuan", method = RequestMethod.POST)
    public ReturnMessage engageTuan(@RequestParam("tuanid") Integer tuanid, @RequestParam("uid") Integer uid) {
        try {
            return pintuanService.insertTuan(tuanid, uid);
        } catch (Exception e) {
            return new ReturnMessage(ResponseCode.SERVER_ERROR, "服务器内部错误");
        }
    }

    @ApiOperation(value = "参团人支付接口", notes = "参团人员参团后，进行支付的接口，需要先调用参团接口")
    @ResponseBody
    @RequestMapping(value = "/engageTuanFinishPay", method = RequestMethod.POST)
    public ReturnMessage engageTuanFinishPay(@RequestParam("tuanid") Integer tuanid, @RequestParam("oid") Integer oid) {
        try {
            return pintuanService.insertTuanFinishPay(tuanid, oid);
        } catch (Exception e) {
            return new ReturnMessage(ResponseCode.SERVER_ERROR, "服务器内部错误");
        }
    }
}