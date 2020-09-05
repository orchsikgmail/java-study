package me.blacksheep;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
	// spring-data-jpa가 제공하는 JpaRepository로 converter 등록
	// implements JpaRepository => 빈 등록 됨
}
