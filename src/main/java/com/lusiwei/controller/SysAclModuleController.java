package com.lusiwei.controller;


import com.lusiwei.common.ResultJson;
import com.lusiwei.dto.SysAclModuleDto;
import com.lusiwei.service.SysAclModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("sys/aclModule")
public class SysAclModuleController {

    private final SysAclModuleService sysAclModuleService;

    @Autowired
    public SysAclModuleController(SysAclModuleService sysAclModuleService) {
        this.sysAclModuleService = sysAclModuleService;
    }

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
    @RequestMapping("delete.json")
    @ResponseBody
    public ResultJson delete(@RequestParam("id") Integer aclModuleId){
        boolean b=sysAclModuleService.deleteByAclId(aclModuleId);
        ResultJson resultJson;
        if (b){
            resultJson=ResultJson.success("删除成功！！");
        }else {
            resultJson=ResultJson.failed("删除失败！该权限模块下有权限点");
        }
        return resultJson;
    }
}
