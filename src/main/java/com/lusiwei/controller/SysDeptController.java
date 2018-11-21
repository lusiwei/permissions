
package com.lusiwei.controller;

import com.lusiwei.common.ResultJson;
import com.lusiwei.dto.SysDeptDto;
import com.lusiwei.service.GetDeptTreeService;
import com.lusiwei.service.SysDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @author lusiwei
 * @date 2018
 * @description : 部门控制器
 */

@Controller
@RequestMapping("sys/dept")
public class SysDeptController {

    private final SysDeptService sysDeptService;
    private final GetDeptTreeService getDeptTreeService;

    @Autowired
    public SysDeptController(SysDeptService sysDeptService, GetDeptTreeService getDeptTreeService) {
        this.sysDeptService = sysDeptService;
        this.getDeptTreeService = getDeptTreeService;
    }

    @RequestMapping("insert.json")
    @ResponseBody
    public ResultJson insert(SysDeptDto sysDeptDto) {
        System.out.println(sysDeptDto);
        sysDeptService.insert(sysDeptDto);
        return ResultJson.success("插入成功");
    }

    @RequestMapping("treeDept.json")
    @ResponseBody
    public ResultJson queryDeptTree() {
        /* return ResultJson.success(sysTreeService.sysDeptTree()); *///老师的方法
        //自己的方法
        return ResultJson.success(getDeptTreeService.getDeptTree());
    }

    @RequestMapping("update.json")
    @ResponseBody
    public ResultJson update(SysDeptDto sysDeptDto) {
        sysDeptService.update(sysDeptDto);
        return ResultJson.success();
    }

    @RequestMapping("dept.page")
    public String deptPage() {
        return "dept";
    }

    /**
     * 删除部门
     */
    @RequestMapping(value = "delete.json")
    @ResponseBody
    public ResultJson delete(@RequestParam("id") Integer deptId) {
        boolean b = sysDeptService.deleteById(deptId);
        ResultJson resultJson;
        if (b){
            resultJson=ResultJson.success("删除成功！！");
        }else {
            resultJson = ResultJson.failed("该部门下有员工，删除失败！！");
        }
        return resultJson;

    }

}
