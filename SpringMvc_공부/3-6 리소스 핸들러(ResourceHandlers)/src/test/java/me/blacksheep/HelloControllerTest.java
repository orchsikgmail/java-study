package me.blacksheep;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.concurrent.TimeUnit;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
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
	public void testHelloStatic() throws Exception {
		this.mockMvc.perform(get("/index.html"))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(content().string(Matchers.containsString("hello index")));
	}
	
	@Test
	public void testHelloMobile() throws Exception {
		this.mockMvc.perform(get("/mobile/index.html"))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(content().string(Matchers.containsString("hello mobile")))
		.andExpect(header().exists(HttpHeaders.CACHE_CONTROL))
		;
	}
	
	

}
