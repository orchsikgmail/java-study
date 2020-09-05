package com.example.demo1;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class AppRunner implements ApplicationRunner {

	@Autowired
	ApplicationContext applicationContext;
	@Autowired
	BookRepository bookRepository;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		/* Environment
			# 프로파일과 프로퍼티를 다루는 인터페이스
			  Environment의 역할은 활성화할 프로파일 확인 및 설정
			# 프로파일 : 빈들의 그룹
			# 사용케이스 : 개발환경에서는 A라는 빈을 사용하고 싶고, 배포환경에서는 B라는 빈을 사용하고 싶다.
		 */
		Environment enviroment = applicationContext.getEnvironment();
		System.out.println(Arrays.toString(enviroment.getActiveProfiles()));
		System.out.println(Arrays.toString(enviroment.getDefaultProfiles()));
	}
}
