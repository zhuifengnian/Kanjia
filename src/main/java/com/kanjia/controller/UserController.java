package com.kanjia.controller;

import com.kanjia.basic.ResponseCode;
import com.kanjia.basic.ReturnMessage;
import com.kanjia.exception.ApiException;
import com.kanjia.pojo.User;
import com.kanjia.service.UserService;
import com.kanjia.utils.QiNiuUtil;
import com.kanjia.wxpay.ConstantUtil;
import com.kanjia.wxpay.TenpayHttpClient;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * <br/>
 * fan 2018/6/13 11:07
 */
@RestController
@RequestMapping("/user")
public class UserController {
    private static final String GET_OPENID_URL = "https://api.weixin.qq.com/sns/jscode2session";

    @Autowired
    private UserService userService;

    @ApiOperation(value = "插入用户", notes = "插入用户到数据库，当用户已经存在时，不插入，会返回这个用户在数据库中的uid")
    @ResponseBody
    @RequestMapping(value="/insertAndLogin", method = RequestMethod.POST)
    public ReturnMessage insertAndLogin(User user){
//        KuaidiLoginUserVO kuaidiLoginUserVO = new KuaidiLoginUserVO();
        //当用户不存在时，在数据库中先记录这个用户
        //在数据库中根据open_id查找用户是否存在
        Integer uid = userService.selectUserByOpenId(user.getOpenId());
        if(uid == null){
            //执行插入操作
            uid = userService.insert(user);
        }
        User user1 = userService.selectUserInfo(uid);
//        ReflectUtil.copyProperties(kuaidiLoginUserVO, kuaiDiUser);
//        //在管理员表中查找用户是否具有管理员权限
//        List<KuaiDiAdmin> kuaiDiAdmins = kuaidiAdminService.selectByUid(uid);
//
//        //当其为管理员时，拿到他可以管理的学校
//        if(kuaiDiAdmins.size() > 0){
//            kuaidiLoginUserVO.setHasAdminPrivate(true);
//            ArrayList<Integer> schoolIds = new ArrayList<>();
//            for(KuaiDiAdmin kuaiDiAdmin: kuaiDiAdmins){
//                schoolIds.add(kuaiDiAdmin.getSchoolId());
//            }
//            kuaidiLoginUserVO.setAdminPermissionShoolId(schoolIds);
//        }

        return new ReturnMessage(ResponseCode.OK, user1);
    }

    @ApiOperation(value = "返回当前用户的openid", notes = "获取用户的openid，这里是通过服务器向微信的服务器发送请求获得的，需要传入js_code")
    @ResponseBody
    @RequestMapping(value="/getopenid", method = RequestMethod.POST)
    public ReturnMessage getopenid(@RequestParam("js_code")String js_code){

        TenpayHttpClient httpClient = new TenpayHttpClient();
        String tmpUrl = GET_OPENID_URL + "?appid="+ConstantUtil.APP_ID +"&secret="+ConstantUtil.APP_SECRET +"&js_code="+js_code+"&grant_type=authorization_code";
        try {
            httpClient.httpGetMethod(tmpUrl);
            String resContent = httpClient.getResContent();
            return new ReturnMessage(ResponseCode.OK, resContent);
        } catch (IOException e) {
            throw new ApiException(ResponseCode.NETWORK_ERROR, "网络请求出现错误");
        }
    }

    @ApiOperation(value = "返回用户详细信息", notes = "返回用户详细信息")
    @ResponseBody
    @RequestMapping(value = "/getUserInfo", method = RequestMethod.GET)
    public ReturnMessage getUserInfo(@RequestParam("uid") Integer uid){
        User userInfoVO = userService.selectUserInfo(uid);
        if(userInfoVO == null){
            throw new ApiException(ResponseCode.PARAM_EROOR, "所传uid没有数据");
        }
        return new ReturnMessage(ResponseCode.OK, userInfoVO);
    }

    @ApiOperation(value = "用户申请成为管理员", notes = "用户申请成为管理员,将来还需传入学校id")
    @ResponseBody
    @RequestMapping(value = "/applyForAdmin", method = RequestMethod.POST)
    public ReturnMessage applyForAdmin(@RequestParam Integer uid){
        return new ReturnMessage(ResponseCode.OK,"we have receive your apply, we will reply you on next few days");
    }

    @ApiOperation(value = "根据用户id获取用户", httpMethod = "GET", notes = "根据用户id获取用户")
    @GetMapping("/getUser")
    public ReturnMessage getUser(@RequestParam Integer uid){
        User user = userService.selectByPrimaryKey(uid);
        return new ReturnMessage(ResponseCode.OK, user);
    }

    @ApiOperation(value = "修改用户头像", httpMethod = "POST", notes = "修改用户头像")
    @PostMapping("/updateAvatar")
    public ReturnMessage updateAvatar(@RequestParam Integer uid, MultipartFile file){
        String avatarUrl = QiNiuUtil.manageFile(file);
        User tmpUser = new User();
        tmpUser.setId(uid);
        tmpUser.setAvatarurl(avatarUrl);
        int i = userService.updateByPrimaryKeySelective(tmpUser);
        return new ReturnMessage(ResponseCode.OK, i);
    }
}