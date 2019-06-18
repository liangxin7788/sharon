package com.fun.business.sharon.config;

import static com.google.common.base.Predicates.or;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.async.DeferredResult;
import static springfox.documentation.builders.PathSelectors.regex;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	@Bean
	public Docket allDocket() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("all").genericModelSubstitutes(DeferredResult.class)
				.useDefaultResponseMessages(false).forCodeGeneration(false).select().build().apiInfo(apiInfo("all"));
	}

	@SuppressWarnings("unchecked")
	@Bean
	public Docket specialDocket() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("user").genericModelSubstitutes(DeferredResult.class)
				.useDefaultResponseMessages(false).forCodeGeneration(false).select()
				// .paths(or(regex("/user/.*"), regex("/list/.*"), regex("/spread/*"),
				// regex("/analysis/chart.*")))
				.paths(or(regex("/user/.*"))).build().apiInfo(apiInfo("用户"));
	}

	private ApiInfo apiInfo(String description) {
		return new ApiInfoBuilder().title("sharon 1.0.1 API")
				.description(String.format("sharon's project %s interface.", description)).version("1.0.0")
				.termsOfServiceUrl("http://www.liangxin.fun/")
//				.contact(new Contact("技术部", "http://www.liangxin.fun/", "lx_4509@163.com"))
				.license("ShenZhen Vilson Technology Co.,Ltd.").licenseUrl("http://www.liangxin.fun/").build();
	}
	
}
