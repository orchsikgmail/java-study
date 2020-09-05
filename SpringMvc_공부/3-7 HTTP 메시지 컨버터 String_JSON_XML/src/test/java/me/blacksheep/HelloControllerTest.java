package me.blacksheep;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.StringWriter;

import javax.xml.bind.Marshaller;
import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamResult;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;


@RunWith(SpringRunner.class)
@SpringBootTest @AutoConfigureMockMvc
// @WebMvcTest : 웹과 관련된 빈만 등록해서 테스트 해준다. ex) @Component와 같은 빈은 등록X
public class HelloControllerTest {

	@Autowired
	MockMvc mockMvc;
	
	// WildCard URL TEST
	@Test
	public void testHello1() throws Exception {
		this.mockMvc.perform(get("/hello/santa"))
		.andDo(print())
		.andExpect(content().string("Your name is santa"));
	}
	
	// Formatter URL TEST
	@Test
	public void testHello2() throws Exception {
		this.mockMvc.perform(get("/hello/santa/12"))
	 	.andDo(print())
	 	.andExpect(content().string("santa,You age is 12"));
	}
	
	// Formatter Prameter TEST
	@Test
	public void testHello3() throws Exception {
		this.mockMvc.perform(get("/hello/age")
				.param("number", "12"))
		.andDo(print())
		.andExpect(content().string("your age is 12"));
	}
	
	// Converter TEST
	@Autowired
	PersonRepository personRepository;
	@Test
	public void testHello4() throws Exception {
		Person person = new Person();
		person.setName("hc");
		Person savedPerson = personRepository.save(person);
		
		this.mockMvc.perform(get("/hello")
				.param("id", savedPerson.getId().toString()))
		.andDo(print())
		.andExpect(content().string("hello, hc"));
	}
	
	// HandlerInteraceptolr TEST
	@Test
	public void testHelloStatic() throws Exception {
		this.mockMvc.perform(get("/index.html"))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(content().string(Matchers.containsString("hello index")));
	}
	
	// ResourceHandler TEST
	@Test
	public void testHelloMobile() throws Exception {
		this.mockMvc.perform(get("/mobile/index.html"))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(content().string(Matchers.containsString("hello mobile")))
		.andExpect(header().exists(HttpHeaders.CACHE_CONTROL))
		;
	}
	
	// HTTP MSG CONVERTER TEST
	// String TEST
	@Test
	public void testRequestBody() throws Exception{
		this.mockMvc.perform(get("/msg")
						.content("hello"))
					.andDo(print())
					.andExpect(status().isOk())
					.andExpect(content().string("hello"));
	}
	
	// JSON TEST
	@Autowired
	private ObjectMapper objectMapper; // 스프링부트에서 자동으로 등록된 빈 - 객체를 jsonString으로 변환할때 사용할꺼다.
	@Test 
	public void  testRequestBody2() throws Exception{
		Person person = new Person();
		person.setId(1000L);
		person.setName("hc");
		String jsonString = objectMapper.writeValueAsString(person);
		
		this.mockMvc.perform(get("/jsonMsg")
						.contentType(MediaType.APPLICATION_JSON_UTF8) // JSON이라는 걸 명시 함
						.accept(MediaType.APPLICATION_JSON_UTF8) // JSON이라는 걸 명시 함
						.content(jsonString))
					.andDo(print())
					.andExpect(status().isOk())
					.andExpect(jsonPath("$.id").value(1000))
					.andExpect(jsonPath("$.name").value("hc"));
	}
	
	// XML TEST
	/*
	@Test 
	public void  testRequestBody3() throws Exception{
		Person person = new Person();
		person.setId(1000L);
		person.setName("hc");

		StringWriter stringWriter = new StringWriter();
		Result result = new StreamResult(stringWriter);
		marshaller.marshal(person, result); // ????
		String xmlString = stringWriter.toString();
		
		this.mockMvc.perform(get("/jsonMsg")
				.contentType(MediaType.APPLICATION_XML) // JSON이라는 걸 명시 함
				.accept(MediaType.APPLICATION_XML) // JSON이라는 걸 명시 함
				.content(xmlString))
		.andDo(print())
		.andExpect(status().isOk());
	} 
	*/
	
	
	
	
	
	

}
