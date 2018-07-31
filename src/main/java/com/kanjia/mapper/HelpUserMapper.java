package com.kanjia.mapper;

import com.kanjia.pojo.HelpUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HelpUserMapper extends BaseMapper<HelpUser> {

    /**
     * 获取订单下帮助者的头像
     * @param oid
     * @param avatarNum
     */
    List<String> getHelperAvatars(@Param("oid") Integer oid,@Param("avatarNum") Integer avatarNum);

}