package me.blacksheepbell;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest
public class Sample2ControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	
	// 미디어타입 설정
	@Test
	public void testMediaTypeHandlerMapping() throws Exception {
		mockMvc.perform(get("/json")
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)  // JSON 형식으로 요청
				.accept(MediaType.APPLICATION_JSON_UTF8_VALUE)  // JSON 형식으로 응답 받음
			)
			.andDo(print())
			.andExpect(content().string("json"));
	}

	
	// 커스터 애노테이션
	@Test
	public void testGoodbye() throws Exception {
		mockMvc.perform(get("/goodbye"))
			.andDo(print())
			.andExpect(content().string("goodbye"));
	}
	
	
	
	
	
	
	
}
