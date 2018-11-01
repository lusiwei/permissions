package com.xmcc.controller;


import com.xmcc.common.ParamValidator;
import com.xmcc.common.ResultJson;
import com.xmcc.exception.ParamException;
import com.xmcc.exception.PermissionException;
import com.xmcc.param.BeanTest;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("userController")
@Slf4j
public class UserController {
    @RequestMapping("test.json")
    @ResponseBody
    public String  test(){
        log.info("hello");
        throw new PermissionException("自定义异常");
    }

    @RequestMapping("test")
    public String test2(){
        throw new RuntimeException("运行时异常");
    }

    @RequestMapping("validate.json")
    public ResultJson validate(BeanTest beanTest) {
        log.info("validate start");
        Map<String, String> map = ParamValidator.validateBean(beanTest);
        if (map != null && map.size() > 0) {
            System.out.println("--------------------"+map);
            throw new ParamException(map.toString());
        }
        return ResultJson.success();
    }
}
