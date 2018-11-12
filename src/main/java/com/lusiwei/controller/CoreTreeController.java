package com.lusiwei.controller;

import com.lusiwei.common.ResultJson;
import com.lusiwei.service.SysCoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: lusiwei
 * @Date: 2018/11/8 15:25
 * @Description:
 */
@Controller
@RequestMapping("/sys/core")
public class CoreTreeController {
    @Autowired
    private SysCoreService sysCoreService;

    @RequestMapping("coreTree.json")
    @ResponseBody
    public ResultJson coreTree(Integer roleId){
        return ResultJson.success(sysCoreService.coreTree(roleId));
    }
}
