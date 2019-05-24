package com.fun.business.sharon.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/***
 * Spring工具类
 * 
 * @author liangxin
 *
 */
@Component
public class SpringUtil implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        SpringUtil.applicationContext = applicationContext;
    }

    public static <E> E getBean(Class<E> clazz) {
        return applicationContext.getBean(clazz);
    }

    public static <E> E getBean(String beanName, Class<E> clazz) {
        return applicationContext.getBean(beanName, clazz);
    }

    public static String getActiveProfile() {
        return applicationContext.getEnvironment().getActiveProfiles()[0];
    }
}
