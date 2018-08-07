package com.kanjia.controller;

import com.kanjia.basic.ResponseCode;
import com.kanjia.basic.ReturnMessage;
import com.kanjia.pojo.EnterpriseBill;
import com.kanjia.pojo.EnterprisePayment;
import com.kanjia.pojo.UserOrder;
import com.kanjia.service.EnterpriseBillService;
import com.kanjia.service.EnterprisePaymentService;
import com.kanjia.service.UserOrderService;
import com.kanjia.vo.EnterprisePaymentPriceVo;
import com.kanjia.vo.EnterprisePaymentVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;

/**
 * liyue 2018/6/29
 */
@RestController
@RequestMapping("/enterprisePayment")
public class EnterprisePaymentController {


    @Autowired
    private EnterprisePaymentService enterprisePaymentService;
    @Autowired
    private EnterpriseBillService enterpriseBillService;


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
//    @RequestMapping(value = "/scanOrders", method = RequestMethod.POST)
//    @ApiOperation(value = "企业扫描二维码", httpMethod = "POST")
//    @ResponseBody
//    public ReturnMessage scanOders(@RequestParam("qr_code") String qr_code) {
//
//        int insert = 0;
//        EnterprisePaymentVo enterprisePaymentVo = userOrderService.getQrCode(qr_code);
//        //修改orders下的状态
//        if (null != enterprisePaymentVo) {
//            UserOrder userOrder = new UserOrder();
//            userOrder.setState(3);
//            userOrder.setId(enterprisePaymentVo.getId());
//            insert = userOrderService.updateByPrimaryKeySelective(userOrder);
//
//
//        }
//
//        return new ReturnMessage(ResponseCode.OK, insert);
//    }

    @RequestMapping(value = "/withdraw", method = RequestMethod.POST)
    @ApiOperation(value = "企业提现", httpMethod = "POST")
    @ResponseBody
    public ReturnMessage withDraw() {


        return new ReturnMessage(ResponseCode.OK, 1);
    }
    @RequestMapping(value = "/enterprisepaymentInfo", method = RequestMethod.POST)
    @ApiOperation(value = "商家端账单中心金额", httpMethod = "POST")
    @ResponseBody
    public ReturnMessage enterprisepaymentInfo(@RequestParam("enterpriseId") Integer enterpriseId) {
             EnterprisePayment enterprisePayment= enterprisePaymentService.getEnterprisePayment(enterpriseId);
        EnterprisePaymentPriceVo enterprisePaymentPriceVo=new EnterprisePaymentPriceVo();
        enterprisePaymentPriceVo.setTotalMoney(enterprisePayment.getTotalMoney());
        enterprisePaymentPriceVo.setWithdrawMoney(enterprisePayment.getWithdrawMoney());
        List<EnterpriseBill> enterpriseBillList=enterpriseBillService.getMoney(enterpriseId);
        BigDecimal decimal = new BigDecimal(0.0);
        for(int i=0;i<enterpriseBillList.size();++i){
            decimal=decimal.add(enterpriseBillList.get(i).getMoney());
        }
        enterprisePaymentPriceVo.setMonthMoney(decimal);
        if(enterprisePayment.getWithdrawMoney()!=null){
        enterprisePaymentPriceVo.setRemainMoney(enterprisePayment.getTotalMoney().subtract(enterprisePayment.getWithdrawMoney()));
        }
        else
        {
            enterprisePaymentPriceVo.setRemainMoney(enterprisePayment.getTotalMoney());

    }
        return  new ReturnMessage(ResponseCode.OK,enterprisePaymentPriceVo);

    }

    }