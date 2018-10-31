package com.xmcc.controller;


import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("userController")
@Slf4j
public class UserController {
    @RequestMapping("test")
    @ResponseBody
    public String  test(){
        log.info("hello");
        return "hello permissions";
    }
}
