package com.xmcc.controller;

import com.xmcc.common.ResultJson;
import com.xmcc.dto.SysDeptDto;
import com.xmcc.service.GetDeptTreeService;
import com.xmcc.service.SysDeptService;
import com.xmcc.service.SysTreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


/**
 * @Author: lusiwei
 * @Date: 2018/11/2 11:34
 * @Description:
 */
@Controller
@RequestMapping("sys/dept")
public class SysDeptController {

    @Autowired
    private SysDeptService sysDeptService;

    @Autowired
    private SysTreeService sysTreeService;

    @Autowired
    private GetDeptTreeService getDeptTreeService;

    @RequestMapping("insert.json")
    @ResponseBody
    public ResultJson insert(SysDeptDto sysDeptDto){
        System.out.println(sysDeptDto);
        sysDeptService.insert(sysDeptDto);
        return ResultJson.success("插入成功");
    }

    @RequestMapping("treeDept.json")
    @ResponseBody
    public ResultJson queryDeptTree(){
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
    public String deptPage(){
        return "dept";
    }
}
