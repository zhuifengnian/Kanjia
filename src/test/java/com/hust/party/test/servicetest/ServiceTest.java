package com.hust.party.test.servicetest;

import com.kanjia.pojo.User;
import com.kanjia.service.UserService;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.*;

/**
 * 专门用来做单元测试<br/>
 * fan 2018/4/26 23:17
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring.xml","classpath:springmvc-config.xml", "classpath:spring-shiro.xml"})
@WebAppConfiguration    //调用javaWEB的组件，比如自动注入ServletContext Bean等等
public class ServiceTest {
    @Autowired
    private UserService userService;

    @Test
    public void testUserService(){
        User record = new User();
        record.setId(55);
        List<User> select = userService.select(record, null);
        System.out.println(select);
    }
}