package com.lusiwei.controller;

import com.lusiwei.common.ResultJson;
import com.lusiwei.dto.PageUtilDto;
import com.lusiwei.dto.SysUserDto;
import com.lusiwei.pojo.SysUser;
import com.lusiwei.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


/**
 * @Author: lusiwei
 * @Date: 2018/11/5 15:41
 * @Description:
 */
@Controller
@RequestMapping("/sys/user")
public class SysUserController {
    private final SysUserService sysUserService;

    @Autowired
    public SysUserController(SysUserService sysUserService) {
        this.sysUserService = sysUserService;
    }

    @RequestMapping("insert.json")
    @ResponseBody
    public ResultJson insert(SysUserDto sysUserDto) {
        sysUserService.insert(sysUserDto);
        return ResultJson.success();
    }

    @RequestMapping("update.json")
    @ResponseBody
    public ResultJson update(SysUserDto sysUserDto) {
        sysUserService.update(sysUserDto);
        return ResultJson.success();
    }

    @RequestMapping("queryAllUser.json")
    @ResponseBody
    public ResultJson query(Integer curPage,Integer pageSize) {
        PageUtilDto pageUtilDto = sysUserService.queryAllUser(curPage, pageSize);

        return ResultJson.success(pageUtilDto);
    }


    @RequestMapping("page.json")
    @ResponseBody
    public ResultJson queryByDeptId(Integer deptId){
        List<SysUser> sysUserList= sysUserService.queryByDeptId(deptId);
        return ResultJson.success(sysUserList);
    }
}
