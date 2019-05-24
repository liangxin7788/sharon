package com.fun.business.sharon.config.datasource;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * 数据源切面
 */
@Component
@Order(value = -100)
@Aspect
@Slf4j
public class DataSourceAspect {
	@Pointcut("execution(* com.fun.business.sharon.biz.business.dao..*.*(..)) ")
    private void sharonAspect() {
    }

    @Pointcut("execution(* com.fun.business.sharon.biz.personal.dao..*.*(..)) ")
    private void otherAspect() {
    }

    @Before("otherAspect()")
    public void other() {
        log.info("切换到 ohter 数据源...");
        DbContextHolder.setDbType(DBEnum.OTHER);
    }

    @Before("sharonAspect()")
    public void sharon() {
    	log.info("切换到 sharon 数据源...");
        DbContextHolder.setDbType(DBEnum.SHARONSOURCE);
    }
}
