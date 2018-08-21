package com.kanjia.service.impl;

import com.kanjia.mapper.BaseMapper;
import com.kanjia.mapper.PintuanMapper;
import com.kanjia.pojo.Pintuan;
import com.kanjia.service.PintuanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 团service<br/>
 * fan 2018/8/21 0:44
 */
@Service
public class PintuanServiceImpl extends AbstractBaseServiceImpl<Pintuan> implements PintuanService {

    @Autowired
    private PintuanMapper pintuanMapper;

    @Override
    public BaseMapper<Pintuan> getDao() {
        return pintuanMapper;
    }


    @Override
    public Integer getPintuanId(Integer oid) {

        return pintuanMapper.getPintuanId(oid);
    }
}