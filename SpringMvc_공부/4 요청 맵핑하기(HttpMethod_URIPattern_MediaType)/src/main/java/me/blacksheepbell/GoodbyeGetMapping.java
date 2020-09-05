package me.blacksheepbell;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/*
 * Retention() : default는 RetentionPolicy.Class 
 * 현재 만드는 애노테이션은 런타임시 요청받은 값을 처리하기 위함이므로 retention정책을 변경해줘야 함
 */
@Retention(RetentionPolicy.RUNTIME) 
@RequestMapping(value="/goodbye", method = RequestMethod.GET)
@Target(ElementType.METHOD)
@Documented
public @interface GoodbyeGetMapping {
}
