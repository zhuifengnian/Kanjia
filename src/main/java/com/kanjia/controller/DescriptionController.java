package com.kanjia.controller;

import com.kanjia.basic.ResponseCode;
import com.kanjia.basic.ReturnMessage;
import com.kanjia.pojo.DescriptionPicture;
import com.kanjia.service.ActivityDescriptionService;
import com.kanjia.service.DescriptionPictureService;
import com.kanjia.utils.JsonUtil;
import com.kanjia.utils.QiNiuUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * liyue 2018/6/29
 */
@RestController
@RequestMapping("/description")
public class DescriptionController {

    @Autowired
    private ActivityDescriptionService activityDescriptionService;
    @Autowired
    private DescriptionPictureService descriptionPictureService;

    @RequestMapping(value = "/activityDescription", method = RequestMethod.POST)
    @ApiOperation(value = "活动详情插入", httpMethod = "POST")
    @ResponseBody
    public ReturnMessage activityDescription(@RequestBody String json) {
        int[] insert = JsonUtil.checkUserIdJson(json);
        return new ReturnMessage(ResponseCode.OK, insert);
    }

    @RequestMapping(value = "/insertDescriptionPicture", method = RequestMethod.POST)
    @ApiOperation(value = "存储活动详情图片信息")
    @ResponseBody
    public ReturnMessage insertDescriptionPicture(@RequestParam("descriptionId") Integer descriptionId, @RequestParam(value = "picture", required = false) MultipartFile flfile) {
        String picture = "";
        DescriptionPicture descriptionPicture = new DescriptionPicture();
        descriptionPicture.setDecriptionId(descriptionId);
        if (flfile != null) {
            picture = QiNiuUtil.manageFile(flfile);
            descriptionPicture.setPicture(picture);
        }

        int insert = descriptionPictureService.updateByPrimaryKeySelective(descriptionPicture);

        return new ReturnMessage(ResponseCode.OK, insert);
    }
}