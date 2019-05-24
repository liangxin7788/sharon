package com.fun.business.sharon;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 项目启动入口
 *
 */
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
@MapperScan("com.fun.business.sharon.biz.*.dao")
public class SharonApplication {
	public static void main(String[] args) {
		SpringApplication.run(SharonApplication.class, args);
		System.out.println("sharon is running, please login on http:locallhost/swagger-ui.html");
	}
}
