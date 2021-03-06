package com.kanjia.controller;

import com.kanjia.basic.Const;
import com.kanjia.basic.PageInfo;
import com.kanjia.basic.ResponseCode;
import com.kanjia.basic.ReturnMessage;
import com.kanjia.pojo.UserOrder;
import com.kanjia.service.HelperService;
import com.kanjia.service.UserOrderService;
import com.kanjia.utils.PageUtil;
import com.kanjia.vo.*;
import com.kanjia.vo.ordervo.OrderDetailVO2;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
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

    @ApiOperation(value = "根据订单类型获取不同订单", notes = "根据订单类型获取不同订单")
    @ResponseBody
    @RequestMapping(value = "/listOrders", method = RequestMethod.GET)
    public ReturnMessage listOrders(@RequestParam("uid") Integer uid, @RequestParam Integer orderState, @RequestParam(required = false, defaultValue = "6") Integer pageSize, @RequestParam(required = false) Integer pageNumber) {
        PageInfo<OrderListVO> orderListVO = userOrderService.listOrders(uid, orderState, PageUtil.setPage(pageSize, pageNumber));
        return new ReturnMessage(ResponseCode.OK, orderListVO);
    }

    @ApiOperation(value = "生成一份订单", notes = "根据用户id和活动id生成一份订单")
    @ResponseBody
    @RequestMapping(value = "/generateOrder", method = RequestMethod.POST)
    public ReturnMessage generateOrder(@RequestParam("uid") Integer uid, @RequestParam("aid") Integer aid) {
        return userOrderService.generateOrder(uid, aid);
    }

    @ApiOperation(value = "根据订单id获取订单详情", notes = "根据订单id获取订单详情，会判断订单是否已过期并改变其状态")
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

    @ApiOperation(value = "根据订单id获取订单详情2", notes = "根据订单id获取订单详情，会判断订单是否已过期并改变其状态")
    @ResponseBody
    @RequestMapping(value = "/getOrderDetail2", method = RequestMethod.GET)
    @ApiResponses({@ApiResponse(code = 200, message = "Successful", response = OrderDetailVO2.class)})
    public ReturnMessage getOrderDetail2(@RequestParam("oid") Integer oid) {
        UserOrder userOrder = userOrderService.selectByPrimaryKey(oid);
        if(userOrder == null){
            return new ReturnMessage(ResponseCode.PARAM_ERROR, "所传订单id不存在");
        }
        OrderDetailVO2 orderDetail2 = userOrderService.getOrderDetail2(oid);
        return new ReturnMessage(ResponseCode.OK, orderDetail2);
    }

    @ApiOperation(value = "根据订单id删除订单", notes = "根据订单id删除订单")
    @ResponseBody
    @RequestMapping(value = "/deleteOrder", method = RequestMethod.POST)
    public ReturnMessage deleteOrder(@RequestParam("oid") Integer oid) {
        try {
            return userOrderService.deleteOrder(oid);
        } catch (Exception e) {
            return new ReturnMessage(ResponseCode.SERVER_ERROR, "服务器内部错误");
        }
    }

    @ApiOperation(value = "订单支付完成接口", notes = "当支付成功后，调用此接口，将订单状态由待付款变成待消费（已付款）的状态，" +
            "若订单未处于待消费状态时，将提示前台不可调用此接口")
    @ResponseBody
    @RequestMapping(value = "/finishPay", method = RequestMethod.POST)
    public ReturnMessage finishPay(@RequestParam("oid") Integer oid) {
        try {
            return userOrderService.finishPay(oid);
        } catch (Exception e) {
            return new ReturnMessage(ResponseCode.SERVER_ERROR, "服务器内部错误");
        }
    }

    @ApiOperation(value = "砍价订单提前结束砍价接口", notes = "只有砍价类型的活动才能调用此接口，它会提前结束正在砍价的状态，" +
            "进入待支付的状态。")
    @ResponseBody
    @RequestMapping(value = "/stopKanjia", method = RequestMethod.POST)
    public ReturnMessage stopKanjia(@RequestParam("oid") Integer oid) {
        try {
            return userOrderService.stopKanjia(oid);
        } catch (Exception e) {
            return new ReturnMessage(ResponseCode.SERVER_ERROR, "服务器内部错误");
        }
    }


}