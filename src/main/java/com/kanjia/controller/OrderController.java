package com.kanjia.controller;

import com.kanjia.basic.Const;
import com.kanjia.basic.PageInfo;
import com.kanjia.basic.ResponseCode;
import com.kanjia.basic.ReturnMessage;
import com.kanjia.pojo.UserOrder;
import com.kanjia.service.HelperService;
import com.kanjia.service.UserOrderService;
import com.kanjia.utils.PageUtil;
import com.kanjia.vo.KanjiaOrderVo;
import com.kanjia.vo.MyOrderVo;
import com.kanjia.vo.OrderDetailVO;
import com.kanjia.vo.OrderHelperVO;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    /**
     * 生成订单
     */
    @ApiOperation(value = "生成一份订单", notes = "根据用户id和活动id生成一份订单")
    @ResponseBody
    @RequestMapping(value = "/generateOrder", method = RequestMethod.POST)
    public ReturnMessage generateOrder(@RequestParam("uid") Integer uid, @RequestParam("aid") Integer aid) {
        //首先判断用户是否有正在砍价的该商品
        UserOrder userOrder = new UserOrder();
        userOrder.setUserId(uid);
        userOrder.setActivityId(aid);
        userOrder.setState(Const.ORDER_STATUS_ENGAGING);    //状态为正在砍价
        List<UserOrder> select = userOrderService.select(userOrder, null);
        if(select.size() > 0){
            return new ReturnMessage(ResponseCode.PARAM_ERROR, "用户已经有这样一份订单正在砍价，不能重新生成");
        }
        //可以生成订单
        Integer oid = userOrderService.generateOrder(uid, aid);
        return new ReturnMessage(ResponseCode.OK, oid);
    }

    /**
     * 根据订单id获取订单详情
     */
    @ApiOperation(value = "根据订单id获取订单详情", notes = "根据订单id获取订单详情")
    @ResponseBody
    @RequestMapping(value = "/getOrderDetail", method = RequestMethod.GET)
    @ApiResponses({@ApiResponse(code = 200, message = "Successful", response = OrderDetailVO.class)})
    public ReturnMessage getOrderDetail(@RequestParam("oid") Integer oid) {
        UserOrder userOrder = userOrderService.selectByPrimaryKey(oid);
        if(userOrder == null){
            return new ReturnMessage(ResponseCode.PARAM_ERROR, "所传订单id不存在");
        }
        OrderDetailVO orderDetail = userOrderService.getOrderDetail(oid);
        return new ReturnMessage(ResponseCode.OK, orderDetail);
    }

}