package com.kanjia.service.impl;

import com.kanjia.mapper.BaseMapper;
import com.kanjia.mapper.UserMapper;
import com.kanjia.pojo.User;
import com.kanjia.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <br/>
 * fan 2018/6/13 10:56
 */
@Service
public class UserServiceImpl extends AbstractBaseServiceImpl<User> implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public BaseMapper<User> getDao() {
        return userMapper;
    }

    @Override
    public Integer selectUserByOpenId(String open_id) {
        return userMapper.selectUserByOpenId(open_id);
    }

    @Override
    public User selectUserInfo(Integer uid) {
        return userMapper.selectUserInfo(uid);
    }

}