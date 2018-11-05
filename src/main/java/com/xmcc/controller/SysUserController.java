package com.xmcc.controller;

import com.xmcc.common.ResultJson;
import com.xmcc.dto.SysUserDto;
import com.xmcc.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: lusiwei
 * @Date: 2018/11/5 15:41
 * @Description:
 */
@Controller
@RequestMapping("/sys/user")
public class SysUserController {
    @Autowired
    private SysUserService sysUserService;

    @RequestMapping("insert.json")
    @ResponseBody
    private ResultJson insert(SysUserDto sysUserDto) {
        sysUserService.insert(sysUserDto);
        return ResultJson.success();
    }

    @RequestMapping("update.json")
    @ResponseBody
    private ResultJson update(SysUserDto sysUserDto) {
        sysUserService.update(sysUserDto);
        return ResultJson.success();
    }
}
