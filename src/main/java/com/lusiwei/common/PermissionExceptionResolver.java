package com.lusiwei.common;

import com.lusiwei.exception.ParamException;
import com.lusiwei.exception.PermissionException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: lusiwei
 * @Date: 2018/10/31 20:54
 * @Description:
 */
@Slf4j
public class PermissionExceptionResolver implements HandlerExceptionResolver {
    private String viewName = "exception";

    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        ModelAndView modelAndView;
        String url = httpServletRequest.getRequestURI();
        log.info(url);
        String message;
        if (e instanceof PermissionException || e instanceof ParamException) {
            log.warn("自定义异常");
            e.printStackTrace();
            message =e.getMessage();
        }else {
            e.printStackTrace();
            message = "系统繁忙";
        }
        if (url.endsWith(".json")) {
            log.warn("json exception");
            viewName = "jsonView";
        } else if (url.endsWith(".page")) {
            log.warn("permissions page exception");
        } else {
            log.warn("unknown exception");
        }
        ResultJson resultJson = ResultJson.failed(message);
        modelAndView = new ModelAndView(viewName, resultJson.toMap());
        return modelAndView;
    }
}
