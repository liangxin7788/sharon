package com.fun.business.sharon;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * 项目启动入口
 *
 */
@SpringBootApplication(scanBasePackages = { "com.fun.business.sharon" }, exclude= {DataSourceAutoConfiguration.class})
@MapperScan("com.fun.business.sharon.biz.*.dao")
public class SharonApplication {
	public static void main(String[] args) {
		SpringApplication.run(SharonApplication.class, args);
		System.out.println("sharon is running, please login on http:localhost:9080/sharon/swagger-ui.html");
	}
}
