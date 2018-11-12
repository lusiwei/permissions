package com.lusiwei.controller;

import com.lusiwei.common.PageCommon;
import com.lusiwei.common.ResultJson;
import com.lusiwei.pojo.SysAcl;
import com.lusiwei.service.SysAclService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
@RequestMapping("sys/acl")
public class SysAclController {
@Autowired
private SysAclService sysAclService;
    @RequestMapping("page.json")
    @ResponseBody
    public ResultJson page(Integer aclModuleId, Integer pageSize, Integer pageNo){
        PageCommon<SysAcl> sysAclPageCommon = sysAclService.queryAclByModuleId(aclModuleId, pageSize, pageNo);
        return ResultJson.success(sysAclPageCommon);
    }
}
