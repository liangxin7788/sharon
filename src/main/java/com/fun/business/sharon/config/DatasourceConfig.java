package com.fun.business.sharon.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.MybatisXMLLanguageDriver;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.fun.business.sharon.common.OperateException;
import com.fun.business.sharon.config.datasource.DBEnum;
import com.fun.business.sharon.config.datasource.DynamicDataSource;
import com.fun.business.sharon.config.datasource.OtherSourcesConfig;
import com.fun.business.sharon.config.datasource.SharonSourcesConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

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

        DruidDataSource sharonSource = getDruidDataSource(sharonSourceConfig.getUrl(), sharonSourceConfig.getUsername(), sharonSourceConfig.getPassword());

        DruidDataSource otherSource = getDruidDataSource(otherSourceConfig.getUrl(), otherSourceConfig.getUsername(), otherSourceConfig.getPassword());

        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DBEnum.SHARONSOURCE.getValue(), sharonSource);
        targetDataSources.put(DBEnum.OTHER.getValue(), otherSource);
        dynamicDataSource.setTargetDataSources(targetDataSources);
        dynamicDataSource.setDefaultTargetDataSource(sharonSource);
        return dynamicDataSource;
    }

    private DruidDataSource getDruidDataSource(String url, String username, String password) {
        DruidDataSource sharonSource = new DruidDataSource();
        sharonSource.setUrl(url);
        sharonSource.setUsername(username);// 用户名
        sharonSource.setPassword(password);// 密码
        try {
            sharonSource.setFilters("com.alibaba.druid.filter.stat.StatFilter");
        } catch (Exception e) {
            throw new OperateException("数据源设置过滤器出错！");
        }
        return sharonSource;
    }

    /**
     * 配置Druid监控启动页面
     */
    @Bean
    public ServletRegistrationBean druidStartViewServlet(){

        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean();
        servletRegistrationBean.setServlet(new StatViewServlet());
        servletRegistrationBean.addUrlMappings("/druid/*");
        Map<String, String> initParameters = new HashMap<>();
        initParameters.put("loginUsername", "admin");// 用户名
        initParameters.put("loginPassword", "weiyue182520");// 密码
        initParameters.put("resetEnable", "false");// 禁用HTML页面上的“Reset All”功能
        servletRegistrationBean.setInitParameters(initParameters);
        return servletRegistrationBean;
    }

    /**
     * Druid监控过滤器配置规则
     *          PS：不知道为啥，这边这么配置了filter之后，界面上还是没有sql记录展示，数据源一项也看不到所使用的filter
     *          解决：在数据源配置中 重新设置 sharonSource.setFilters("com.alibaba.druid.filter.stat.StatFilter");
     */
    @Bean
    public FilterRegistrationBean druidStartFilter(){

        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new WebStatFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }

}
