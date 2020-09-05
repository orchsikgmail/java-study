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

	// DispatcherServlet�� �����ϴ� �⺻���� �̿��ϰ� ���� ���� ���, MVC ������Ҹ� ���� ������ ����ϰ�, ����� �����Ͽ� ����� �� �ִ�.
	// ������ �̷��� �������� �ʴ´�... �� ���� ����� �־
	/*
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
		internalResourceViewResolver.setPrefix("/WEB-INF/");
		internalResourceViewResolver.setSuffix(".jsp");
		return internalResourceViewResolver;
	}
	*/
	
	
	// �� ���� ��� 
	// @EnabledWebMvc ����, WebMvcConfigurer ����
	public void configureViewResolvers(ViewResolverRegistry registry) {
		registry.jsp("/WEB-INF/", ".jsp");
	}

	
	// �پ��� ������ �����ϴ�.
	public void addFormatters(FormatterRegistry registry) {
	}

	
	public void addInterceptors(InterceptorRegistry registry) {
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
