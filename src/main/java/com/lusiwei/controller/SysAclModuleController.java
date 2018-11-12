package com.lusiwei.controller;


import com.lusiwei.common.ResultJson;
import com.lusiwei.dto.SysAclModuleDto;
import com.lusiwei.service.SysAclModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("sys/aclModule")
public class SysAclModuleController {

    @Autowired
    private SysAclModuleService sysAclModuleService;

    @RequestMapping("dept.page")
    public String deptPage() {

        return "dept";
    }

    @RequestMapping("insert.json")
    public @ResponseBody
    ResultJson insert(SysAclModuleDto sysAclModuleDto) {
        sysAclModuleService.insert(sysAclModuleDto);
        return ResultJson.success("插入成功");
    }

    @RequestMapping("fullTree.json")
    public @ResponseBody
    ResultJson fullTree() {
        return ResultJson.success(sysAclModuleService.sysAclModuleTree());
    }

    @RequestMapping("update.json")
    public @ResponseBody
    ResultJson update(SysAclModuleDto sysAclModuleDto) {
        sysAclModuleService.update(sysAclModuleDto);
        return ResultJson.success();
    }

    @RequestMapping("acl.page")
    public String aclPage() {
        return "acl";
    }
}
