package com.example.demo1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class BookServiceRunner implements ApplicationRunner {

	@Autowired
	BookService bookService;
	@Autowired
	ApplicationContext applicationContext;  // 빈을 담고있는 애플리케이션 설정정보 읽어올수있다.
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		bookService.printBookRepository();
		
		AutowiredAnnotationBeanPostProcessor bean = applicationContext.getBean(AutowiredAnnotationBeanPostProcessor.class);
		System.out.println(bean);
	}
	
	
	
	
	
	
}
