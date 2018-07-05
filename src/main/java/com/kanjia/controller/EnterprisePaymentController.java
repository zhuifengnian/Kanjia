package com.kanjia.controller;

import com.kanjia.basic.ResponseCode;
import com.kanjia.basic.ReturnMessage;
import com.kanjia.pojo.EnterprisePayment;
import com.kanjia.pojo.UserOrder;
import com.kanjia.service.EnterprisePaymentService;
import com.kanjia.service.UserOrderService;
import com.kanjia.vo.EnterprisePaymentVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * liyue 2018/6/29
 */
@RestController
@RequestMapping("/enterprisePayment")
public class EnterprisePaymentController {


    @Autowired
    private EnterprisePaymentService enterprisePaymentService;
    @Autowired
    private UserOrderService userOrderService;


    //    @RequestMapping(value = "/scanOrders", method = RequestMethod.POST)
//    @ApiOperation(value = "企业扫描二维码", httpMethod = "POST")
//    @ResponseBody
//    public ReturnMessage scanOders(@RequestParam("qr_code") String qr_code) {
//
//        int[] insert = new int[2];
//        EnterprisePaymentVo enterprisePaymentVo = userOrderService.getQrCode(qr_code);
//        //修改orders下的状态
//        if (null != enterprisePaymentVo) {
//            UserOrder userOrder = new UserOrder();
//            userOrder.setState(3);
//            userOrder.setId(enterprisePaymentVo.getId());
//            insert[0] = userOrderService.updateByPrimaryKeySelective(userOrder);
//
//            EnterprisePayment enterprisePayment = enterprisePaymentService.getEnterprisePayment(enterprisePaymentVo.getEnterpriseId());
//
//            enterprisePayment.setTotalMoney(enterprisePayment.getTotalMoney().add(enterprisePaymentVo.getMinuPrice()));
//            insert[1] = enterprisePaymentService.updateByPrimaryKeySelective(enterprisePayment);
//
//        }
//
//        return new ReturnMessage(ResponseCode.OK, insert);
//    }
    @RequestMapping(value = "/scanOrders", method = RequestMethod.POST)
    @ApiOperation(value = "企业扫描二维码", httpMethod = "POST")
    @ResponseBody
    public ReturnMessage scanOders(@RequestParam("qr_code") String qr_code) {

        int insert = 0;
        EnterprisePaymentVo enterprisePaymentVo = userOrderService.getQrCode(qr_code);
        //修改orders下的状态
        if (null != enterprisePaymentVo) {
            UserOrder userOrder = new UserOrder();
            userOrder.setState(3);
            userOrder.setId(enterprisePaymentVo.getId());
            insert = userOrderService.updateByPrimaryKeySelective(userOrder);


        }

        return new ReturnMessage(ResponseCode.OK, insert);
    }

    @RequestMapping(value = "/withdraw", method = RequestMethod.POST)
    @ApiOperation(value = "企业提现", httpMethod = "POST")
    @ResponseBody
    public ReturnMessage withDraw() {


        return new ReturnMessage(ResponseCode.OK, 1);
    }

}