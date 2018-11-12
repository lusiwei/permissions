package com.lusiwei.controller;

import com.lusiwei.common.ResultJson;
import com.lusiwei.dto.RoleUserDto;
import com.lusiwei.service.SysRoleUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: lusiwei
 * @Date: 2018/11/12 13:14
 * @Description:
 */
@Controller
@RequestMapping("/sys/roleUser")
public class RoleUserController {
    @Autowired
    private SysRoleUserService sysRoleUserService;

    @RequestMapping("users.json")
    @ResponseBody
    public ResultJson queryUsersByRoleId(Integer roleId){
        RoleUserDto roleUserDto=sysRoleUserService.queryUserByRoleId(roleId);
        return ResultJson.success(roleUserDto);
    }

    @RequestMapping("changeUsers.json")
    @ResponseBody
    public ResultJson changeUsers(Integer roleId,Integer[] userIds){
        sysRoleUserService.insertUserByRoleId(roleId,userIds);
        return ResultJson.success();
    }
}
