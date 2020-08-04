package com.lugew.winsim.entity.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringContextUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    public static <T> T getBean(Class<T> cls) {
        if (applicationContext == null) {
            throw new RuntimeException("applicationContext注入失败");
        }
        return applicationContext.getBean(cls);
    }

    public static Object getBean(String name) {
        if (applicationContext == null) {
            throw new RuntimeException("applicationContext注入失败");
        }
        return applicationContext.getBean(name);
    }

    public static <T> T getBean(String name, Class<T> cls) {
        if (applicationContext == null) {
            throw new RuntimeException("applicationContext注入失败");
        }
        return applicationContext.getBean(name, cls);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextUtil.applicationContext = applicationContext;
    }
}