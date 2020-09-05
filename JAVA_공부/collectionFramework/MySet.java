package interviews.java.collectionFramework;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

class MySet {

	/*
	 * 중복값이 저장되지 않는다는 점. 
	 * 그 점을 제외하고 맵과 동일
	 */
	
	@Test
	public void setExample() {
		final Set<String> set = new HashSet<>();
		set.add("hello");
		set.add("hello");
		set.add("welcome");
		set.add("goodbye");
		
		assertEquals(3, set.size());
	}
	
	
}
