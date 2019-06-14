package com.fun.business.sharon.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {
	@Bean
	public RestTemplate initRestTemplate() {
//		HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory(); // 以前jpa架构用这个
		SimpleClientHttpRequestFactory httpRequestFactory = new SimpleClientHttpRequestFactory();
//		httpRequestFactory.setConnectionRequestTimeout(160000);
		httpRequestFactory.setConnectTimeout(160000);
		httpRequestFactory.setReadTimeout(160000);
		return new RestTemplate(httpRequestFactory);
	}

}
