package com.kanjia.controller;

import com.kanjia.basic.ResponseCode;
import com.kanjia.basic.ReturnMessage;
import com.kanjia.pojo.ActivityDescription;
import com.kanjia.pojo.DescriptionPicture;
import com.kanjia.service.ActivityDescriptionService;
import com.kanjia.service.DescriptionPictureService;
import com.kanjia.utils.JsonUtil;
import com.kanjia.utils.QiNiuUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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
        List<ActivityDescription>  activityDescriptionList= JsonUtil.checkUserIdJson(json);
        int []insert=new int[activityDescriptionList.size()];
        for(int i=0;i<activityDescriptionList.size();++i){
            insert[i]=activityDescriptionService.insert(activityDescriptionList.get(i));
        }
        return new ReturnMessage(ResponseCode.OK, insert);
    }
    @RequestMapping(value = "/modifyDscription", method = RequestMethod.POST)
    @ApiOperation(value = "修改活动详情", httpMethod = "POST")
    @ResponseBody
    public ReturnMessage modifyDscription(@RequestBody String json) {

        List<ActivityDescription>  activityDescriptionList= JsonUtil.checkUserIdJson(json);
        int[] insert = new int[activityDescriptionList.size()];

        if(activityDescriptionList!=null) {
            activityDescriptionService.delete(activityDescriptionList.get(0).getActivityId());
            for (int i = 0; i < activityDescriptionList.size(); ++i) {
                insert[i] = activityDescriptionService.insert(activityDescriptionList.get(i));
            }
        }
        return new ReturnMessage(ResponseCode.OK, insert);
    }
    @RequestMapping(value = "/deleteDescriptionPicture", method = RequestMethod.POST)
    @ApiOperation(value = "删除活动详情图片信息")
    @ResponseBody
    public ReturnMessage deleteDescriptionPicture(@RequestParam("activityId") Integer activityId) {

      Integer insert=  descriptionPictureService.delete(activityId);

        return new ReturnMessage(ResponseCode.OK, insert);
    }

    @RequestMapping(value = "/insertDescriptionPicture", method = RequestMethod.POST)
    @ApiOperation(value = "存储活动详情图片信息")
    @ResponseBody
    public ReturnMessage insertDescriptionPicture(@RequestParam("activityId") Integer activityId, @RequestParam(value = "picture", required = false) MultipartFile flfile) {

        String picture = "";
        DescriptionPicture descriptionPicture = new DescriptionPicture();
        descriptionPicture.setActivityId(activityId);
        if (flfile != null) {
            picture = QiNiuUtil.manageFile(flfile);
            descriptionPicture.setPicture(picture);
        }

        int insert = descriptionPictureService.insert(descriptionPicture);

        return new ReturnMessage(ResponseCode.OK, insert);
    }
}