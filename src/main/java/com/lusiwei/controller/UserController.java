package com.lusiwei.controller;


import com.lusiwei.common.ApplicationContextHelper;
import com.lusiwei.util.BeanValidator;
import com.lusiwei.common.ResultJson;
import com.lusiwei.dao.SysUserMapper;
import com.lusiwei.exception.PermissionException;
import com.lusiwei.param.BeanTest;
import com.lusiwei.pojo.SysUser;
import com.lusiwei.util.JsonUtil;
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
