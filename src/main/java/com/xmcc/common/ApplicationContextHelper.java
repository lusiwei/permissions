package com.xmcc.common;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @Author: lusiwei
 * @Date: 2018/11/1 15:55
 * @Description:
 */
@Component
public class ApplicationContextHelper implements ApplicationContextAware {
    private static ApplicationContext ac;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ac=applicationContext;
    }

    /**
     * 根据接口类型获得子类
     */
    public  static <T> T getBean(Class<T> tClass) {
        return ac.getBean(tClass);
    }

    /**
     * 根据name 和接口类型获得子类
     */
    public static <T> T getBean(String name, Class<T> tClass) {
        return ac.getBean(name, tClass);
    }
}
