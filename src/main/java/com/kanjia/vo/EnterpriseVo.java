package com.kanjia.vo;

import java.util.Date;
import java.util.List;

/**
 * Created by liyue on 2018/6/29
 */

public class EnterpriseVo {


    private Integer enteprirseId;

    private String enterpriseName;

    private String enterprisePhone;

    private String longitude;

    private String latitude;

    private String address;
    public Integer getEnteprirseId() {
        return enteprirseId;
    }

    public void setEnteprirseId(Integer enteprirseId) {
        this.enteprirseId = enteprirseId;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public String getEnterprisePhone() {
        return enterprisePhone;
    }

    public void setEnterprisePhone(String enterprisePhone) {
        this.enterprisePhone = enterprisePhone;
    }


    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
