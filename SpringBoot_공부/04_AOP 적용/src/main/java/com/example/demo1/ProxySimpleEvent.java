/* 그냥 공통기능을 구현한 클래스를 만들고, 기능을 구현해주고 싶은 빈을 호출해서 처리함.
 * 단점: 해당 빈 하나하나 호출 해줘야함 
 * -> AOP사용, pointcut 설정을 통해 적용하고 싶은 클래스에 일괄적으로 적용 가능.
 * 	 pom.xml에 라이브러리 추가 해줘야함.
 * 
 
  package com.example.demo1;
 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;


@Primary
@Service
public class ProxySimpleEvent implements EventService {

	@Autowired
	SimpleEventService simpleEventService;
	
	
	@Override
	public void createEvent() {
		long begin = System.currentTimeMillis();
		simpleEventService.createEvent();
		System.out.println(System.currentTimeMillis() - begin);
	}
	
	
	@Override
	public void publishEvent() {
		long begin = System.currentTimeMillis();
		simpleEventService.publishEvent();
		System.out.println(System.currentTimeMillis() - begin);
	}

	
	@Override
	public void deleteEvent() {
		
		simpleEventService.deleteEvent();
		
	}

	
}


*/