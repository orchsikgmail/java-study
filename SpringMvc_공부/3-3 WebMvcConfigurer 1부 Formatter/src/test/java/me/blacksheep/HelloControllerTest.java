package me.blacksheep;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


@RunWith(SpringRunner.class)
@SpringBootTest @AutoConfigureMockMvc
// @WebMvcTest : 웹과 관련된 빈만 등록해서 테스트 해준다. ex) @Component와 같은 빈은 등록X
public class HelloControllerTest {

	@Autowired
	MockMvc mockMvc;
	
	
	@Test
	public void testHello1() throws Exception {
		this.mockMvc.perform(get("/hello/santa"))
		.andDo(print())
		.andExpect(content().string("Your name is santa"));
	}
	
	
	@Test
	public void testHello2() throws Exception {
		this.mockMvc.perform(get("/hello/santa/12"))
	 	.andDo(print())
	 	.andExpect(content().string("santa,You age is 12"));
	}
	
	
	@Test
	public void testHello3() throws Exception {
		this.mockMvc.perform(get("/hello/age")
				.param("number", "12"))
		.andDo(print())
		.andExpect(content().string("your age is 12"));
	}
	
	
	

}
