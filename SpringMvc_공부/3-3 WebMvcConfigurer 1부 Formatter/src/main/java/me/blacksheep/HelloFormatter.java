package me.blacksheep;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

@Component
public class HelloFormatter implements Formatter<Person>{
	
	// 문자열을 객체로 전환
	@Override
	public Person parse(String text, Locale locale) throws ParseException {
		Person person = new Person();
		person.setAge(text);
		return person;
	}
	
	
	// 객체를 문자열로 전환
	@Override
	public String print(Person object, Locale locale) {
		return null;
	}
	
	
}
