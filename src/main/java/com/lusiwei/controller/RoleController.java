package com.lusiwei.controller;

import com.lusiwei.common.ResultJson;
import com.lusiwei.dto.RoleDto;
import com.lusiwei.pojo.SysRole;
import com.lusiwei.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author: lusiwei
 * @Date: 2018/11/8 09:42
 * @Description:
 */
@Controller
@RequestMapping("/sys/role")
public class RoleController {
    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @RequestMapping("role.page")
    public String rolePage(){
        return "role";
    }

    @RequestMapping("insert.json")
    @ResponseBody
    public ResultJson insert(RoleDto roleDto){
        roleService.insert(roleDto);
        return ResultJson.success("插入成功");
    }

    @RequestMapping("update.json")
    @ResponseBody
    public ResultJson update(RoleDto roleDto){
        roleService.update(roleDto);
        return ResultJson.success("修改成功");
    }

    @RequestMapping("queryAllRole.json")
    @ResponseBody
    public ResultJson query(){
        List<SysRole> sysRoleList=roleService.queryAllRole();
        return ResultJson.success(sysRoleList);
    }
}
