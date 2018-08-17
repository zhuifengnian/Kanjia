package com.kanjia.controller;

import com.kanjia.basic.ResponseCode;
import com.kanjia.basic.ReturnMessage;
import com.kanjia.service.TenPayService;
import com.sun.deploy.net.HttpResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Produces;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

/**
 * 微信相关接口<br/>
 * fan 2018/8/17 12:06
 */
@Controller
@RequestMapping("/wechat")
public class WeChatController {
    @Autowired
    private TenPayService tenPayService;

    @RequestMapping(value = "/requestMiniCodePicture", method = RequestMethod.GET, produces = "image/png")
    @ResponseBody
    @ApiOperation(value = "申请小程序码图片接口，需要传入路径，会返回小程序码图片", httpMethod = "GET")
    public void requestMiniCodePicture(@ApiParam(required = true,
            value = "必填，需要生成小程序码的路径，如pages/index/index")@RequestParam("page") String page, @ApiParam(required = false,
            value = "可选，给小程序码传入参数，如a=123&b=ttt，记得好像有大小限制") @RequestParam(required = false, defaultValue = "a=123") String scene, HttpServletResponse response){
        try {
            tenPayService.requestMiniCodePicture(page, scene, response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}