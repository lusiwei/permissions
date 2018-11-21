package com.lusiwei.controller;

import com.lusiwei.common.PageCommon;
import com.lusiwei.common.ResultJson;
import com.lusiwei.dto.RoleUserSetDto;
import com.lusiwei.pojo.SysAcl;
import com.lusiwei.service.SysAclService;
import com.lusiwei.service.SysRoleAclService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("sys/acl")
public class SysAclController {

    private final SysAclService sysAclService;
    private final SysRoleAclService sysRoleAclService;

    @Autowired
    public SysAclController(SysAclService sysAclService, SysRoleAclService sysRoleAclService) {
        this.sysAclService = sysAclService;
        this.sysRoleAclService = sysRoleAclService;
    }

    @RequestMapping("page.json")
    @ResponseBody
    public ResultJson page(Integer aclModuleId, Integer pageSize, Integer pageNo) {
        PageCommon<SysAcl> sysAclPageCommon = sysAclService.queryAclByModuleId(aclModuleId, pageSize, pageNo);
        return ResultJson.success(sysAclPageCommon);
    }

    @RequestMapping("aclRoles.json")
    @ResponseBody
    public ResultJson queryAllRoleByAclId(Integer aclId) {
        RoleUserSetDto roleUserSetDto = sysRoleAclService.queryAllRoleByAclId(aclId);
        return ResultJson.success(roleUserSetDto);
    }


}
