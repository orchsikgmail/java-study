package com.example.demo1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class AppRunner implements ApplicationRunner {

	@Autowired
	Single single;
	@Autowired
	Proto proto;
	@Autowired
	ApplicationContext applicationContext;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		// @Scope("prototype")을 주면 해당 빈은 빈을 호출할 때마다 새로운 객체를 생성해서 반환한다.
		// @Scope()의 기본값은 싱글톤이다.
		System.out.println(proto);
		System.out.println(single.getProto());
		
		System.out.println("Single");
		System.out.println(applicationContext.getBean(Single.class));
		System.out.println(applicationContext.getBean(Single.class));
		System.out.println(applicationContext.getBean(Single.class));
		
		System.out.println("Proto");
		System.out.println(applicationContext.getBean(Proto.class));
		System.out.println(applicationContext.getBean(Proto.class));
		System.out.println(applicationContext.getBean(Proto.class));

		
		// 싱글에서 프로토를 사용할 때 원래 의도대로 되지않는다.
		// 싱글이 단 한번 객체 생성시 한번 프로토를 호출하고  더 이상 부르지 않기때문
		/* 해결방법
		 	1) proxyMode를 설정해준다. 기본값은 프록시모드사용안함이므로
		 	2) @Autowired을 통해 의존성을 주입할때 ObjectProvider<Proto>타입으로 사용 - 비추
		 */
		System.out.println("Proto by Single");
		System.out.println(applicationContext.getBean(Single.class).getProto());
		System.out.println(applicationContext.getBean(Single.class).getProto());
		System.out.println(applicationContext.getBean(Single.class).getProto());
		
		
	}

	
	
}
