package com.lusiwei.filter;

import com.lusiwei.pojo.SysUser;
import com.lusiwei.util.ThreadLocalCommon;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: lusiwei
 * @Date: 2018/11/6 16:06
 * @Description:
 */
public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest= (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse= (HttpServletResponse) servletResponse;
        SysUser sysUser = (SysUser) httpServletRequest.getSession().getAttribute("sysUser");
        if (sysUser == null) {
            httpServletResponse.sendRedirect("/signin.jsp");
            return;
        }
        ThreadLocalCommon.pushSysUser(sysUser);
        ThreadLocalCommon.pushHttpServletRequest(httpServletRequest);
        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }

    @Override
    public void destroy() {

    }
}
