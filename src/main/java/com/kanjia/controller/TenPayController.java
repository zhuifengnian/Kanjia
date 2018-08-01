package com.kanjia.controller;

import com.kanjia.basic.ResponseCode;
import com.kanjia.basic.ReturnMessage;
import com.kanjia.exception.ApiException;
import com.kanjia.pojo.User;
import com.kanjia.pojo.UserOrder;
import com.kanjia.service.TenPayService;
import com.kanjia.service.UserOrderService;
import com.kanjia.service.UserService;
import com.kanjia.wxpay.PrepayIdRequestHandler;
import com.kanjia.wxpay.XMLUtil;
import io.swagger.annotations.ApiOperation;
import org.jdom2.JDOMException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Map;

@Controller
@Scope("prototype")
public class TenPayController {

    @Autowired
    private UserOrderService userOrderService;
    @Autowired
    private UserService userService;
    @Autowired
    private TenPayService tenpayService;

    /**
     * 点赞完成后，生成预支付订单，获取prepayId
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/app/tenpay/orderPrepay", method = RequestMethod.POST)
    @ApiOperation(value = "订单调用此接口，生成预支付订单，获取prepayId", httpMethod = "POST")
    public @ResponseBody
    Map<String, Object> orderPrepay(@RequestParam("uid") Integer uid, @RequestParam("oid") Integer oid, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        //拿到用户数据
        User user = userService.selectByPrimaryKey(uid);
        if(user == null){
            throw new ApiException(ResponseCode.PARAM_ERROR, "所给用户id不存在");
        }
        String openid = user.getOpenId();

        // 获取生成预支付订单的请求类
        PrepayIdRequestHandler prepayReqHandler = new PrepayIdRequestHandler(request, response);
        //拿到所传订单
        UserOrder orders = userOrderService.selectByPrimaryKey(oid);
        if(orders == null){
            throw new ApiException(ResponseCode.PARAM_ERROR, "所给订单id不存在");
        }
        Integer aid = orders.getActivityId();

        //拿到订单所应支付的金额
        BigDecimal currentPrice = orders.getCurrentPrice();

        Map<String, Object> map = tenpayService.getPrepayId(openid, currentPrice, false, request, response);

        return map;
    }

    /**
     * 接收微信支付成功通知
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/app/tenpay/notify", method = RequestMethod.GET)
    @ApiOperation(value = "获取微信支付后台的通知", httpMethod = "get")
    public void getnotify(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        System.out.println("微信支付回调");
        PrintWriter writer = response.getWriter();
        InputStream inStream = request.getInputStream();
        ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = inStream.read(buffer)) != -1) {
            outSteam.write(buffer, 0, len);
        }
        outSteam.close();
        inStream.close();
        String result = new String(outSteam.toByteArray(), "utf-8");
        System.out.println("微信支付通知结果：" + result);
        Map<String, String> map = null;
        try {
            /**
             * 解析微信通知返回的信息
             */
            map = XMLUtil.doXMLParse(result);
        } catch (JDOMException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/refund", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "退款接口，会将金额退还给用户，该功能还在施工中", httpMethod = "post")
    public ReturnMessage refund(@RequestParam("uid") Integer uid, @RequestParam("oid") Integer oid){
        //TODO:退款操作
        return new ReturnMessage(ResponseCode.OK, "收到退款请求，客服将会尽快审核");
    }

}