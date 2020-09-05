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
		Ű-�� ���� ��Ÿ���� Entry��� ����Ŭ������ ����.
		HashMap�� ���ҵ��� Entry ��ü�� �迭 �Ǵ� ����Ʈ�� ����ȴ�.
		Hash���� �̿��� �����ϹǷ� ������ ����.
		Comparable�� ������ Ű���� ��ü�� �ִ� ��� �⺻������ ����
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
		���� Ʈ�� �ڷᱸ���� �̿��Ѵ�. ������ Ʈ���� �� ��尡 Ű-�� ���� �ȴ�.
		Comparable�� Comparator �������̽��� ����Ѵ�.
		Ű�� ������ ������ ������ ���� �����ϱ� ������ hashCode�� ������� �ʴ´�.
		# �������̽� : ���ĵǾ����� �ϴ°��, ������ �������Ƿ� �����˻��� ���� -> �����˻� �ʿ��Ҷ�
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

	// Comparator ��ü�� �Ű������� �־� �����ϸ�, TreeMap���ҵ��� ����� ���� ���� ����
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
		�⺻������ HashMapŬ������ ���� ������� �۵�.
		������ ������ �����Ҷ��� ����
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
