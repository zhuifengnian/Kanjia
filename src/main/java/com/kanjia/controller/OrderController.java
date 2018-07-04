package com.kanjia.controller;

import com.kanjia.basic.PageInfo;
import com.kanjia.basic.ResponseCode;
import com.kanjia.basic.ReturnMessage;
import com.kanjia.pojo.UserOrder;
import com.kanjia.service.UserOrderService;
import com.kanjia.utils.PageUtil;
import com.kanjia.vo.KanjiaOrderVo;
import com.kanjia.vo.MyOrderVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 订单的controller<br/>
 * fan 2018/7/2 16:39
 */
@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private UserOrderService userOrderService;

    @ApiOperation(value = "根据活动id获得其下所有订单", notes = "获取活动下的所有订单列表")
    @ResponseBody
    @RequestMapping(value = "/getOrdersByAid", method = RequestMethod.POST)
    public ReturnMessage getOrdersByAid(@RequestParam("aid") Integer aid, @RequestParam(required = false, defaultValue = "4") Integer pageSize, @RequestParam(required = false) Integer pageNumber) {
        PageInfo<UserOrder> pageInfo = userOrderService.getOrdersByAid(aid, PageUtil.setPage(pageSize, pageNumber));
        return new ReturnMessage(ResponseCode.OK, pageInfo);
    }

    @ApiOperation(value = "获取用户正在砍价的订单", notes = "获取用户正在砍价的订单，用于我的砍价页面，默认6条")
    @ResponseBody
    @RequestMapping(value = "/listKanjiaOrders", method = RequestMethod.GET)
    public ReturnMessage listKanjiaOrders(@RequestParam("uid") Integer uid, @RequestParam(required = false, defaultValue = "6") Integer pageSize, @RequestParam(required = false) Integer pageNumber) {
        PageInfo<KanjiaOrderVo> kanjiaOrderVos = userOrderService.listKanjiaOrders(uid, PageUtil.setPage(pageSize, pageNumber));
        return new ReturnMessage(ResponseCode.OK, kanjiaOrderVos);
    }
    @ApiOperation(value = "获取用户我的订单数据", notes = "获取用户我的订单，用于我的订单页面，默认6条")
    @ResponseBody
    @RequestMapping(value = "/listMyOrders", method = RequestMethod.GET)
    public ReturnMessage listMyOrders(@RequestParam("uid") Integer uid, @RequestParam(required = false, defaultValue = "6") Integer pageSize, @RequestParam(required = false) Integer pageNumber) {
        PageInfo<MyOrderVo> myOrderVoPageInfo = userOrderService.listMyOrders(uid, PageUtil.setPage(pageSize, pageNumber));
        return new ReturnMessage(ResponseCode.OK, myOrderVoPageInfo);
    }

}