
/* 따로 설정파일 만들기 귀찮아서 전체주석함
 * 주입하고 싶은 TestBookRepository클래스에 @Component @Profile 처리함
 * 
 * 
package com.example.demo1;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class TestConfiguration {
	// 각각 클래스에 어노테이션으로 빈 설정하지않고, 현재 클래스에 빈설정을 해준다.
	// @Profile을 이용해서 프로파일 적용
	// 서버설정 -> H=Argument -> -Dspring.profiles.active=test 입력
	// 	OR  서버설정 -> SpringBoot -> profile ->  test 입력
	// 위와 같이 서버설정한 경우만, test프로파일을 설정한 빈 적용됨
	@Bean
	public BookRepository bookRepository() {
		return new TestBookRepository();
	}
}
*/