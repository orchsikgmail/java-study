package com.example.demo1;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

// AOP 사용

@Component
@Aspect
public class PerfAspect {
	
	// bean 으로 설정
	/*
	@Around("bean(simpleEventService)")
	public Object logperf(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		
		long begin = System.currentTimeMillis();
		Object retValObject = proceedingJoinPoint.proceed(); 
		System.out.println(System.currentTimeMillis() - begin);
		
		return retValObject;
	}
	 */
	
	
	// pointcut 표현식으로 설정
	/*
	@Around("execution(* com.example..*.EventService.*(..))")
	public Object logperf(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		
		long begin = System.currentTimeMillis();
		Object retValObject = proceedingJoinPoint.proceed(); 
		System.out.println(System.currentTimeMillis() - begin);
		
		return retValObject;
	}
	*/
	
	
	// 커스텀어노테이션 설정 후, 해당 어노테이션 달아준 부분만 적용
	@Around("@annotation(PerfLogging)")
	public Object logperf(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		
		long begin = System.currentTimeMillis();
		Object retValObject = proceedingJoinPoint.proceed(); 
		System.out.println(System.currentTimeMillis() - begin);
		
		return retValObject;
	}
	
	
	/*
	 * Around : 메소드 전과정
	 * Before : 메소드 호출 전
	 *   ... 많이 있음.
	 */
	@Before("bean(simpleEventService)")
	public void sayHello() {
		System.out.println("hello");
	}
	
	
}
