package com.lusiwei.controller;

import com.lusiwei.common.ResultJson;
import com.lusiwei.service.SysRoleAclService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: lusiwei
 * @Date: 2018/11/12 11:06
 * @Description:
 */
@Controller
@RequestMapping("/sys/roleAcl")
public class RoleAclController {

    @Autowired
    private SysRoleAclService sysRoleAclService;

    @RequestMapping("changeAcls.json")
    @ResponseBody
    public ResultJson change(@Param("roleId") Integer roleId, @Param("aclIds") Integer[] aclIds) {
        for (Integer aclId : aclIds) {
            System.out.println(aclId);
        }
        sysRoleAclService.saveAcls(roleId,aclIds);
        return ResultJson.success();
    }
}
