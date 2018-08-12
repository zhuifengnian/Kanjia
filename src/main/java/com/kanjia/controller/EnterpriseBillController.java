package com.kanjia.controller;

import com.kanjia.basic.PageInfo;
import com.kanjia.basic.ResponseCode;
import com.kanjia.basic.ReturnMessage;
import com.kanjia.pojo.Bill;
import com.kanjia.service.EnterpriseBillService;
import com.kanjia.utils.PageUtil;
import com.kanjia.vo.BillInfoVo;
import com.kanjia.vo.EnterpriseBillVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * liyue 2018/6/29
 */
@RestController
@RequestMapping("/enterpriseBill")
public class EnterpriseBillController {


    @Autowired
    private EnterpriseBillService enterpriseBillService;


    @RequestMapping(value = "/billList", method = RequestMethod.POST)
    @ApiOperation(value = "账单列表", httpMethod = "POST")
    @ResponseBody
    public ReturnMessage billList(@RequestParam("enterpriseId") Integer enterpriseId,@RequestParam(required = false) Integer pageSize, @RequestParam(required = false) Integer pageNumber) {
       PageInfo<List<Bill>> enterpriseBillVoList= enterpriseBillService.getListBill(enterpriseId,PageUtil.setPage(pageSize,pageNumber));

        return new ReturnMessage(ResponseCode.OK, enterpriseBillVoList);
    }
    @RequestMapping(value = "/billInfo", method = RequestMethod.POST)
    @ApiOperation(value = "账单详情", httpMethod = "POST")
    @ResponseBody
    public ReturnMessage billInfo(@RequestParam("bill_id")Integer bid) {
        BillInfoVo billInfoVo=enterpriseBillService.getBillInfo(bid);
        return new ReturnMessage(ResponseCode.OK, billInfoVo);
    }

    }