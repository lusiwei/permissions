package com.lusiwei.controller;

import com.lusiwei.common.ResultJson;
import com.lusiwei.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: lusiwei
 * @Date: 2018/11/5 22:21
 * @Description:
 */
@Controller
@RequestMapping("login")
public class LoginController {

    private final SysUserService sysUserService;

    @Autowired
    public LoginController(SysUserService sysUserService) {
        this.sysUserService = sysUserService;
    }

    @RequestMapping("login.json")
    @ResponseBody
    private ResultJson login(String account, String password, HttpServletRequest httpServletRequest) {
        ResultJson login = sysUserService.login(account, password);
        if (login.isResult()) {
            httpServletRequest.getSession().setAttribute("sysUser", login.getData());
        }
        return login;
    }

    @RequestMapping("logout.page")
    public String logout(HttpServletRequest request){
        request.getSession().invalidate();
        return "redirect:/signin.jsp";
    }
}
