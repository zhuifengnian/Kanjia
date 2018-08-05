package com.kanjia.controller;

import com.kanjia.basic.ResponseCode;
import com.kanjia.basic.ReturnMessage;
import com.kanjia.pojo.HelpUser;
import com.kanjia.pojo.User;
import com.kanjia.pojo.UserOrder;
import com.kanjia.service.HelperService;
import com.kanjia.service.UserOrderService;
import com.kanjia.service.UserService;
import com.kanjia.vo.OrderDetailVO;
import com.kanjia.vo.OrderHelperVO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 帮助者controller<br/>
 * fan 2018/8/5 9:12
 */
@Controller
@RequestMapping("/helper")
public class HelperController {
    @Autowired
    private UserOrderService userOrderService;
    @Autowired
    private HelperService helperService;
    @Autowired
    private UserService userService;

    @ApiOperation(value = "根据订单id获取所有帮助者数据", notes = "根据订单id获取所有帮助者数据")
    @ResponseBody
    @RequestMapping(value = "/listOrderHelpers", method = RequestMethod.GET)
    public ReturnMessage listOrderHelpers(@RequestParam("oid") Integer oid) {
        UserOrder userOrder = userOrderService.selectByPrimaryKey(oid);
        if(userOrder == null){
            return new ReturnMessage(ResponseCode.PARAM_ERROR, "所传订单id不存在");
        }
        List<OrderHelperVO> orderHelperVOS = helperService.listOrderHelpers(oid);
        return new ReturnMessage(ResponseCode.OK, orderHelperVOS);
    }

    @ApiOperation(value = "帮助者帮砍订单", notes = "帮助者帮砍订单")
    @ResponseBody
    @RequestMapping(value = "/helpKanjia", method = RequestMethod.POST)
    @ApiResponses({@ApiResponse(code = 200, message = "Successful", response = HelpUser.class)})
    public ReturnMessage helpKanjia(@RequestParam("oid") Integer oid, @RequestParam Integer uid) {
        UserOrder userOrder = userOrderService.selectByPrimaryKey(oid);
        if(userOrder == null){
            return new ReturnMessage(ResponseCode.PARAM_ERROR, "所传订单id不存在");
        }
        User user = userService.selectByPrimaryKey(uid);
        if(user == null){
            return new ReturnMessage(ResponseCode.PARAM_ERROR, "所传用户id不存在");
        }
        ReturnMessage returnMessage = helperService.helpKanjia(userOrder, user);
        return  returnMessage;
    }
}