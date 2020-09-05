package com.example.demo1;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

	@Autowired  
	// @Qualifier("abookRepository")  // 스프링 빈설정 xml파일 만들어야 하나?
	// @Primary : @Qualifier대신 사용하고자하는 클래스에 달아줘도 된다.
	// @Primary사용함
	BookRepository bookRepository;
	
	// 배열사용해서 전부 받아올 수도 있음
	// List<BookRepository> bookRepositories;
	
	
	public void printBookRepository() {
		System.out.println(bookRepository.getClass());

		// 리스트로 모든 빈 가져올때.
		//this.bookRepositories.forEach(x -> System.out.println(x));
			// this.bookRepositories.forEach(System.out::println);
	}
	
	
	// Autowired이후 작동되는 동작.
	@PostConstruct
	public void printBookRepository2() {
		System.out.println(bookRepository.getClass());
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
