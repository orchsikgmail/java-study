package me.blacksheep;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	// 내가 만든 formatter를 등록한다.
	/* SpringBoot는 그냥 Formatter 빈등록만 해주면 된다
	@Override
	public void addFormatters(FormatterRegistry registry) {
		registry.addFormatter(new HelloFormatter());
	}
	*/
	
}
