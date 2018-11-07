package com.lusiwei.util;

import com.lusiwei.pojo.SysUser;

import javax.servlet.http.HttpServletRequest;

public class ThreadLocalCommon {
    //绑定当前用户
    private static final ThreadLocal<SysUser> USER_THREAD_LOCAL =new ThreadLocal<>();

    //绑定
    private static final ThreadLocal<HttpServletRequest> REQUEST_LOCAL =new ThreadLocal<>();

    public static void pushSysUser(SysUser sysUser){
        USER_THREAD_LOCAL.set(sysUser);
    }

    public static SysUser popSysUser(){
       return USER_THREAD_LOCAL.get();
    }
    public static void removeSysUser(){
        USER_THREAD_LOCAL.remove();
    }

    public static void pushHttpServletRequest(HttpServletRequest request){
        REQUEST_LOCAL.set(request);
    }

    public static HttpServletRequest popHttpServletRequest(){
        return REQUEST_LOCAL.get();
    }
    public static void removeHttpServletRequest(){
        REQUEST_LOCAL.remove();
    }

}
