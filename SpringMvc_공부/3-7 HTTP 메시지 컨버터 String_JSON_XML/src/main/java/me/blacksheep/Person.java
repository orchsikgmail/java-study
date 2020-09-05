package me.blacksheep;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

@Entity  // ID이용 Converter사용 하려고
@XmlRootElement // HTTP Msg Converter - XML
public class Person {

	private String name;
	private String age;
	private String hobby;
	@Id // 해당 멤버변수를 Person의 식별자로 지정
	@GeneratedValue // 자동생성
	private Long id;
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
