package com.lusiwei.util;

import java.lang.annotation.*;

/**
 * @Author: lusiwei
 * @Date: 2018/11/13 00:02
 * @Description:
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AclHelp {
    //权限点名字
    String value() default "";

    //模塊id
    int module_id();
}
