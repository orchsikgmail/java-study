package me.blacksheep;

import java.util.concurrent.TimeUnit;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	
	/* HandlerInterceptor 구현
	  1. HandlerInterceptor를 구현하는 클래스를 생성.
	  2. WebConfigure클래스에서 등록. 
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
	
		// 순서 order() 지정 안하면, 등록순서대로 진행
		// preHandler1 - preHandler2 - 요청처리 - postHandler2 - postHandler1 - 뷰랜더링 - afterCompletion2 - afterCompletion1
		/*
		registry.addInterceptor(new OneInterceptor());
		registry.addInterceptor(new TwoInterceptor());
		*/
		
		registry.addInterceptor(new OneInterceptor()).order(1000); // .order()메소드로 순서 변경 가능 *Handler2 -> *Handler1
		registry.addInterceptor(new TwoInterceptor())
			.addPathPatterns("/hello") // 특정 URL에서만 작동하도록 가능.
			.order(0);
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/mobile/**")
			.addResourceLocations("classpath:/mobile/") // classpath: <- src/main/java || src/main/resources
			.setCacheControl(CacheControl.maxAge(10, TimeUnit.MINUTES));
	}

	
	
	
	
	
}
