package interviews.java.collectionFramework;


import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

import org.junit.jupiter.api.Test;


class MyMap {
	
	
	/* HashMap
		키-값 쌍을 나타내는 Entry라는 내부클래스가 존재.
		HashMap의 원소들은 Entry 객체의 배열 또는 리스트로 저장된다.
		Hash값을 이용해 저장하므로 순서가 없다.
		Comparable이 구현된 키값을 객체로 주는 경우 기본정렬을 따름
	 */
	@Test
	public void overwriteKey() {
		final Map<String, String> preferences = new HashMap<String, String>(); 
		preferences.put("like", "pizza");
		preferences.put("dislike", "coke");

		assertEquals("pizza", preferences.get("like"));

		preferences.put("like", "chicken");

		assertEquals("chicken", preferences.get("like"));
	}
	
	
	/* TreeMap
		이진 트리 자료구조를 이용한다. 따리서 트리의 각 노드가 키-값 쌍이 된다.
		Comparable과 Comparator 인터페이스를 사용한다.
		키를 정렬한 가능한 순서에 따라 저장하기 때문에 hashCode를 사용하지 않는다.
		# 유즈케이스 : 정렬되었으면 하는경우, 정렬이 되있으므로 범위검색이 가능 -> 범위검색 필요할때
	 */
	@Test
	public void treeMapTraversal() {
		final TreeMap<Integer, String> counts = new TreeMap<Integer, String>();

		counts.put(4, "four");
		counts.put(1, "one");
		counts.put(3, "three");
		counts.put(2, "two");

		final Iterator<Integer> keys = counts.keySet().iterator();
		int i =1;

		while(keys.hasNext()) {
			int key = keys.next();
			assertEquals(i, key);
			i++;
		}
	}

	// Comparator 객체를 매개변수로 주어 생성하면, TreeMap원소들을 사용자 정의 정렬 가능
	@Test
	public void reverseTreeMapTraversal() {
		class Comp implements Comparator<Integer>{
			@Override
			public int compare(Integer o1, Integer o2) {
				if(o1>o2) {
					return -1;
				} else if(o1<o2) {
					return 1;
				}
				return 0;
			}
		}
		
		final TreeMap<Integer, String> counts = new TreeMap<Integer, String>(
				new Comp()
				);

		counts.put(4, "four");
		counts.put(1, "one");
		counts.put(3, "three");
		counts.put(2, "two");

		final Iterator<Integer> keys = counts.keySet().iterator();
		int i =4;

		while(keys.hasNext()) {
			int key = keys.next();
			assertEquals(i, key);
			i--;
		}
	}


	/* LinkedHashMap
		기본적으로 HashMap클래스와 같은 방식으로 작동.
		원소의 정렬이 삽입할때와 동일
	 */
	@Test
	public void linkedHashMapTraversal() {
		final Map<Integer, String> counts = new LinkedHashMap<>();
	
		counts.put(4, "four");
		counts.put(1, "one");
		counts.put(3, "three");
		counts.put(2, "two");
	
		final Iterator<Integer> keys = counts.keySet().iterator();
		assertEquals(4, keys.next());
		assertEquals(1, keys.next());
		assertEquals(3, keys.next());
		assertEquals(2, keys.next());
		assertFalse(keys.hasNext());
	}















}
