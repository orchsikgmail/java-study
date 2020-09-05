package com.example.demo1;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// AOP적용을 위해 만든 커스텀 어노테이션
// 이 어노테이션을 사용하면 성능을 확인해줍니다.

@Documented  // javadoc으로 api문서를 만들 때 해당 어노테이션에 대한 설명 포함하도록 지정
@Target(ElementType.METHOD) // 어노테이션이 적용될 위치
@Retention(RetentionPolicy.CLASS) // 어노테이션 유지정책
public @interface PerfLogging {}
