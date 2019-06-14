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
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
@MapperScan("com.fun.business.sharon.biz.*.dao")
public class SharonApplication {
	public static void main(String[] args) {
		SpringApplication.run(SharonApplication.class, args);
		System.out.println("sharon is running, please login on http:locallhost/swagger-ui.html");
	}
//	
//	@Bean
//	public RestTemplate initRestTemplate() {
//		HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
//		httpRequestFactory.setConnectionRequestTimeout(160000);
//		httpRequestFactory.setConnectTimeout(160000);
//		httpRequestFactory.setReadTimeout(160000);
//		return new RestTemplate(httpRequestFactory);
//	}
//	
}
