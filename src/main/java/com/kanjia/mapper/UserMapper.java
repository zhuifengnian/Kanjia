package com.kanjia.mapper;

import com.kanjia.pojo.User;

public interface UserMapper extends BaseMapper<User>{
    Integer selectUserByOpenId(String open_id);

    User selectUserInfo(Integer uid);
}