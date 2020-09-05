package interviews.java.collectionFramework;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

class MySet {

	/*
	 * �ߺ����� ������� �ʴ´ٴ� ��. 
	 * �� ���� �����ϰ� �ʰ� ����
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
