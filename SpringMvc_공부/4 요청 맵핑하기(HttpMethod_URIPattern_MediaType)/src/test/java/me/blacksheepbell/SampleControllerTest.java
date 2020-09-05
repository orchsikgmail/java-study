package me.blacksheepbell;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)  // TEST 한다~
@WebMvcTest // 어떤? WebMvc -> 자동 @MockMvc 빈 등록 한다.
public class SampleControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void helloTest() throws Exception {
		// RequestMethod TEST
		mockMvc.perform(get("/hello"))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(content().string("hello"))
		;
		mockMvc.perform(post("/hello"))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(content().string("hello"))
		;
		mockMvc.perform(patch("/hello"))
		.andDo(print())
		.andExpect(status().isMethodNotAllowed())
		.andExpect(content().string(""))
		;

		// ? TEST : ?개수만큼 아무 문자 맵핑가능
		mockMvc.perform(get("/anyword"))
		.andDo(print())
		.andExpect(status().is(404))
		.andExpect(content().string(""))
		;
		mockMvc.perform(get("/anywordAA"))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(content().string("anywordOk"))
		;
		
		// * TESET : 한덩어리의 문자열 맵핑가능, / 구분자 허용 X
		mockMvc.perform(get("/manywordAAAAAAAAAAAAAAAAAA"))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(content().string("manywordsssOk"))
		;
		mockMvc.perform(get("/manywordAAA/BBB"))
		.andDo(print())
		.andExpect(status().is(404))
		.andExpect(content().string(""))
		;
		
		// ** TEST : 하위 모든 방식 매핑가능
		mockMvc.perform(get("/starManywordCCC/AAA/BBB"))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(content().string("starManywordsssOk"))
		;
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}




}
