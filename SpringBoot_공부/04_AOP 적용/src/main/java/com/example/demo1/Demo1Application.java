package com.example.demo1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// 스프링 AOP : 프록시 기반 AOP

// AppRunnner : client
// EventService : subject
// SeimpleEventService : real subject
// client, real subject 클래스의 코드 변경 없이 realsubject 런타임시 기능을 추가해보자

// 추가할 기능 : 각 메소드 실행 시간을 측정  <- 성능측정이라는 공통의관심사를 다른 클래스들에서도 사용할 수 있기때문에 aspect로 지정해서 추가해보자
@SpringBootApplication
public class Demo1Application {
	
	public static void main(String[] args) {
		// SpringApplication.run(Demo1Application.class, args);
		
		// SpringBoot에 관한 내용
		// 웹애플리케이션을 띄우지않고, 그냥 자바 메인 메소드 실행하듯이 실행하는 방법. 
		SpringApplication application = new SpringApplication(new Demo1Application().getClass());
		application.setWebApplicationType(WebApplicationType.NONE);
		application.run(args);
		
	}
	
	
	
}
