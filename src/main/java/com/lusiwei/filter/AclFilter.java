package com.lusiwei.filter;

import com.lusiwei.common.ApplicationContextHelper;
import com.lusiwei.common.ResultJson;
import com.lusiwei.pojo.SysUser;
import com.lusiwei.service.SysCoreService;
import com.lusiwei.util.JsonUtil;
import com.lusiwei.util.RequestHolder;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

/**
 * @Author: lusiwei
 * @Date: 2018/11/13 09:22
 * @Description:
 */
@Slf4j
public class AclFilter implements Filter {

    private static List<String> exclusionList;

    @Override
    public void init(FilterConfig filterConfig) {
        String exclusionPath = filterConfig.getInitParameter("exclusionPath");
        InputStream resourceAsStream = AclFilter.class.getClassLoader().getResourceAsStream(exclusionPath);
        Properties p = new Properties();
        try {
            p.load(resourceAsStream);
            String freeAcl = p.getProperty("freeAcl");
            exclusionList = Arrays.stream(freeAcl.split(",")).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        //获取当前请求路径
        String servletPath = httpServletRequest.getServletPath();
        System.out.println("---------------------------");
        System.out.println(servletPath);
        //如果访问的地址在白名单中，直接放过
        if (exclusionList.contains(servletPath)) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        //获取当前用户
        SysUser currentUser = RequestHolder.getCurrentUser();
        if (currentUser == null) {
            noAuth(httpServletRequest, httpServletResponse);
        }
        SysCoreService coreService = ApplicationContextHelper.getBean(SysCoreService.class);
        if (!coreService.hasUrlAcl(servletPath)) {
            noAuth(httpServletRequest, httpServletResponse);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private void noAuth(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        String servletPath = httpServletRequest.getServletPath();

        final String[] requestSuffix = {".json"};
        if (servletPath.endsWith(requestSuffix[0])) {
            ResultJson resultJson = ResultJson.failed("没有访问权限，请联系管理员");
            httpServletResponse.setContentType("application/json;charset=utf-8");
            httpServletResponse.getWriter().write(JsonUtil.object2String(resultJson));
            return;
        } else {
            httpServletResponse.sendRedirect("/noAuth.jsp");
        }
    }

    @Override
    public void destroy() {

    }
}
