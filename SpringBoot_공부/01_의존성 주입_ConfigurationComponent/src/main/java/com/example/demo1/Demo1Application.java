package com.example.demo1;

import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import com.example.demo1out.CBookService;

@SpringBootApplication
public class Demo1Application {

	@Autowired
	CBookService cBookService;
	
	public static void main(String[] args) {
		// static 정적 메소드 run사용
		// SpringApplication.run(Demo1Application.class, args);

		
		// 다른 패키지의 클래스를 빈으로등록하고 싶다. => 직접 객체로 만들어서 초기화해주면서 빈으로 등록, @Component등록 필요없음
		SpringApplication app = new SpringApplication(Demo1Application.class);
		
		app.addInitializers(new ApplicationContextInitializer<GenericApplicationContext>() {
			@Override
			public void initialize(GenericApplicationContext applicationContext) {
				// 타 패키지의 사용자 정의 클래스를 빈으로 등록
				applicationContext.registerBean(CBookService.class);
			 
				
				// ApplicationRunner를 빈으로 등록하고, Supplier클래스로 등록한 빈 클래스의 메소드 구현
				applicationContext.registerBean(ApplicationRunner.class, new Supplier<ApplicationRunner>() {
					@Override
					public ApplicationRunner get() {
						return new ApplicationRunner() {
							@Override
							public void run(ApplicationArguments args) throws Exception {
								System.out.println("함수로 빈을 정의해봤다!!!");
							}
						};
					}
					
				});
			}
		});
		app.run(args);
		
	}

}
