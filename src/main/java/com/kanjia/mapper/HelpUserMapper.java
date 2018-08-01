package com.kanjia.mapper;

import com.kanjia.pojo.HelpUser;
import com.kanjia.vo.OrderHelperVO;
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
    List<String> getOrderHelperAvatars(@Param("oid") Integer oid, @Param("avatarNum") Integer avatarNum);

    /**
     * 获取订单下的帮助者信息，包括头像，昵称，帮砍价格
     */
    List<OrderHelperVO> getOrderHelpers(@Param("oid") Integer oid, @Param("num") Integer num);

}