package me.blackbell;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ComponentScan
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

	// DispatcherServlet이 제공하는 기본값을 이용하고 싶지 않은 경우, MVC 구성요소를 직접 빈으로 등록하고, 사용자 정의하여 사용할 수 있다.
	// 하지만 이렇게 설정하지 않는다... 더 편한 방법이 있어서
	/*
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
		internalResourceViewResolver.setPrefix("/WEB-INF/");
		internalResourceViewResolver.setSuffix(".jsp");
		return internalResourceViewResolver;
	}
	*/
	
	
	// 더 좋은 방법 
	// @EnabledWebMvc 설정, WebMvcConfigurer 구현
	public void configureViewResolvers(ViewResolverRegistry registry) {
		registry.jsp("/WEB-INF/", ".jsp");
	}

	
	// 다양한 설정이 가능하다.
	public void addFormatters(FormatterRegistry registry) {
	}

	
	public void addInterceptors(InterceptorRegistry registry) {
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
