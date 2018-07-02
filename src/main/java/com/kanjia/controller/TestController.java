package com.kanjia.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 测试controller<br/>
 * fan 2018/6/13 10:34
 */
@Controller
@ApiIgnore
public class TestController {
    @RequestMapping("/index")
    public String index() {
        return "/index.jsp";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }
}