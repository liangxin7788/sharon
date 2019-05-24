package com.fun.business.sharon.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.MybatisXMLLanguageDriver;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.fun.business.sharon.config.datasource.DBEnum;
import com.fun.business.sharon.config.datasource.DynamicDataSource;
import com.fun.business.sharon.config.datasource.OtherSourcesConfig;
import com.fun.business.sharon.config.datasource.SharonSourcesConfig;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;

/**
 * mybatis-plus相关配置
 */
@Slf4j
@Configuration
public class DatasourceConfig {

	@Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        log.info(dataSource.getClass().toString());

        MybatisSqlSessionFactoryBean sqlSessionFactory = new MybatisSqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(dataSource);
        sqlSessionFactory.setTypeAliasesPackage("com.fun.business.sharon.biz.*.bean");

        sqlSessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:/mapper/*.xml"));
        MybatisConfiguration configuration = new MybatisConfiguration();
        configuration.setDefaultScriptingLanguage(MybatisXMLLanguageDriver.class);
        configuration.setJdbcTypeForNull(JdbcType.NULL);
        configuration.setMapUnderscoreToCamelCase(true);
        sqlSessionFactory.setConfiguration(configuration);
        PaginationInterceptor pagination = new PaginationInterceptor();
        sqlSessionFactory.setPlugins(new Interceptor[]{
                pagination
        });
        return sqlSessionFactory.getObject();
    }

    @Bean
    public DataSource buildDataSource(SharonSourcesConfig sharonSourceConfig, OtherSourcesConfig otherSourceConfig) {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        DruidDataSource sharonSource = new DruidDataSource();
        sharonSource.setUrl(sharonSourceConfig.getUrl());
        sharonSource.setUsername(sharonSourceConfig.getUsername());// 用户名
        sharonSource.setPassword(sharonSourceConfig.getPassword());// 密码

        DruidDataSource otherSource = new DruidDataSource();
        otherSource.setUrl(otherSourceConfig.getUrl());
        otherSource.setUsername(otherSourceConfig.getUsername());// 用户名
        otherSource.setPassword(otherSourceConfig.getPassword());// 密码

        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DBEnum.SHARONSOURCE.getValue(), sharonSource);
        targetDataSources.put(DBEnum.OTHER.getValue(), otherSource);
        dynamicDataSource.setTargetDataSources(targetDataSources);
        dynamicDataSource.setDefaultTargetDataSource(sharonSource);
        return dynamicDataSource;
    }
	
}
