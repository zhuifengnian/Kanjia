package com.kanjia.controller;

import com.kanjia.basic.Page;
import com.kanjia.basic.PageInfo;
import com.kanjia.basic.ResponseCode;
import com.kanjia.basic.ReturnMessage;
import com.kanjia.pojo.Admin;
import com.kanjia.pojo.AdminEnterpriseInfo;
import com.kanjia.pojo.Enterprise;
import com.kanjia.pojo.EnterprisePayment;
import com.kanjia.service.AdminService;
import com.kanjia.service.EnterprisePaymentService;
import com.kanjia.service.EnterpriseService;
import com.kanjia.utils.PageUtil;
import com.kanjia.vo.adminvo.AdminVo;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * liyue 2018/6/29
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private EnterpriseService enterpriseService;
    @Autowired
    private EnterprisePaymentService enterprisePaymentService;

    @ApiOperation(value = "插入管理员或者企业用户", notes = "插入管理员和企业")
    @ResponseBody
    @RequestMapping(value = "/insertAdminEnterprise", method = RequestMethod.POST)
    public ReturnMessage insertAdminEnterprise(@RequestParam("type") Integer id, AdminEnterpriseInfo adminEnterpriseInfo) {
        Integer enid = enterpriseService.getId(adminEnterpriseInfo.getOpenId());
        Integer anid=adminService.getId(adminEnterpriseInfo.getOpenId());
        if(enid!=null){
            Enterprise enterprise1 = enterpriseService.selectByPrimaryKey(enid);
            return new ReturnMessage(ResponseCode.OK, enterprise1);
        }
        else if(anid!=null){
            Admin admin1 = adminService.selectByPrimaryKey(anid);
            return new ReturnMessage(ResponseCode.OK, admin1);
        }
        else {
            if (id == 1) {
                Enterprise enterprise = new Enterprise();
                enterprise.setAvatarurl(adminEnterpriseInfo.getAvatarurl());
                enterprise.setNickname(adminEnterpriseInfo.getNickname());
                enterprise.setOpenId(adminEnterpriseInfo.getOpenId());
                enterprise.setCityName(adminEnterpriseInfo.getCity());
                enterprise.setProvinceName(adminEnterpriseInfo.getProvince());
                enterprise.setGender(adminEnterpriseInfo.getGender());
                return insertAndLogin(enterprise);
            } else if (id == 2) {
                Admin admin = new Admin();
                admin.setAvatarurl(adminEnterpriseInfo.getAvatarurl());
                admin.setNickname(adminEnterpriseInfo.getNickname());
                admin.setOpenId(adminEnterpriseInfo.getOpenId());
                return insertPuAdmin(admin);
            } else {
                Admin admin = new Admin();
                admin.setAvatarurl(adminEnterpriseInfo.getAvatarurl());
                admin.setNickname(adminEnterpriseInfo.getNickname());
                admin.setOpenId(adminEnterpriseInfo.getOpenId());
                return insertChaoAdmin(admin);
            }
        }
    }
    @ApiOperation(value = "管理员获取商家信息列表", notes = "管理员获取商家信息列表")
    @ResponseBody
    @RequestMapping(value = "/getListEnterprise", method = RequestMethod.POST)
    public ReturnMessage getListEnterprise(@RequestParam("admin_id") Integer id,@RequestParam(required = false) Integer pageSize, @RequestParam(required = false) Integer pageNumber) {
        Admin admin=adminService.selectByPrimaryKey(id);
        PageInfo <AdminVo> adminVoList=new PageInfo<>();
        if(admin!=null&&admin.getState()==1){
           adminVoList =adminService.getListEnterprise(PageUtil.setPage(pageSize, pageNumber));
        }
      return   new ReturnMessage(ResponseCode.OK, adminVoList);
    }
    public ReturnMessage insertPuAdmin(Admin admin) {
        //当管理员不存在时，在数据库中先记录这个；
        //在数据库中根据open_id查找用户是否存在
            //执行插入操作
            admin.setState(1);
            admin.setIdentity(1);
            Integer  anid = adminService.insert(admin);
        Admin admin1=new Admin();
        if(anid!=null) {
           admin1  = adminService.selectByPrimaryKey(anid);

        }
        return new ReturnMessage(ResponseCode.OK, admin1);
    }
    public ReturnMessage insertChaoAdmin(Admin admin) {
        //当管理员不存在时，在数据库中先记录这个；
        //在数据库中根据open_id查找用户是否存在
            //执行插入操作
            admin.setState(1);
            admin.setIdentity(2);
           Integer anid = adminService.insert(admin);
        Admin admin1=new Admin();
        if(anid!=null) {
            admin1  = adminService.selectByPrimaryKey(anid);

        }
        return new ReturnMessage(ResponseCode.OK, admin1);
    }

    public ReturnMessage insertAndLogin(Enterprise enterprise) {
        //当商家不存在时，在数据库中先记录这个；否则直接返回商家
        //在数据库中根据open_id查找用户是否存在

            //执行插入操作
            enterprise.setState(1);
           Integer enid = enterpriseService.insert(enterprise);
            if (enid != null) {
                //商户账户
                EnterprisePayment enterprisePayment = new EnterprisePayment();
                enterprisePayment.setEnterpriseId(enid);

                enterprisePaymentService.insert(enterprisePayment);
            }

        Enterprise enterprise1 = enterpriseService.selectByPrimaryKey(enid);
        return new ReturnMessage(ResponseCode.OK, enterprise1);
    }
}