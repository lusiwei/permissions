package com.xmcc.controller;


import com.xmcc.common.ApplicationContextHelper;
import com.xmcc.util.BeanValidator;
import com.xmcc.common.ResultJson;
import com.xmcc.dao.SysUserMapper;
import com.xmcc.exception.PermissionException;
import com.xmcc.param.BeanTest;
import com.xmcc.pojo.SysUser;
import com.xmcc.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lusiwei
 */
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
    @ResponseBody
    public ResultJson validate(BeanTest beanTest) {
        log.info("validate start");
        List<BeanTest> beanTestList=new ArrayList<>();
        beanTestList.add(beanTest);
        BeanValidator.check(beanTestList);
        return ResultJson.success();
    }

    @RequestMapping("query.json")
    @ResponseBody
    public ResultJson queryUser(){
        System.out.println("-----------");
        SysUserMapper sysUserMapper = ApplicationContextHelper.getBean(SysUserMapper.class);
        System.out.println("sysUserMapper"+sysUserMapper);
        SysUser sysUser = sysUserMapper.selectByPrimaryKey(1);
        System.out.println(sysUser);
        String string = JsonUtil.object2String(sysUser.toString());
        log.info(string);
        return ResultJson.success(string);
    }

}
