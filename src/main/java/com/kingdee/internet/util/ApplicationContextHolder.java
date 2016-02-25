package com.kingdee.internet.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class ApplicationContextHolder implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ApplicationContextHolder.applicationContext = applicationContext;
    }

    public static <T> T getBean(Class<T> requiredType) {
        checkNotNull();
        return applicationContext.getBean(requiredType);
    }

    public static <T> T getBean(String name, Class<T> requiredType) {
        checkNotNull();
        return applicationContext.getBean(name, requiredType);
    }

    public static ApplicationContext applicationContext() {
        checkNotNull();
        return applicationContext;
    }

    public static void checkNotNull() {
        Assert.notNull(applicationContext, "application context not been initialized.");
    }
}
