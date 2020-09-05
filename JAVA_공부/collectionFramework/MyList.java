package interviews.java.collectionFramework;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

import org.junit.jupiter.api.Test;

class MyList {

	@Test
	@SuppressWarnings("unused")
	public void arrayDefinitions() {
		final int[] integers = new int[3]; // 명시적 크기 지정
		final boolean[] booleans = {true, false, false, true}; // 암시적 크기 지정
		final String[] strings = new String[] {"aaa", "bbbb"};
		
		final Random r = new Random();
		final String[] randomArrayLength = new String[r.nextInt(100)];
	}
	
	
	@Test
	public void arrayCopy() {
		// 배열 전체를 사용중일 때, 크기가 큰 배열을 만들고 기존 배열의 값들을 붙여넣어준다.
		// 기존배열에 큰배열의 주소값을 넣어서 큰배열을 사용할수있게한다.
		int[] integers = {1,2,3};
		int[] newIntegers = new int[5];
		
		System.arraycopy(integers, 0, newIntegers, 0, integers.length);
		
		integers = newIntegers;
		integers[4] = 4;
		
		assertEquals(4, integers[4]);
	}
	
	// ArrayList 클래스는 리스트의 데이터로 배열을 사용하는 List 인터페이스의 구현체다.
	// 시작위치나 중간위치에 새로운 원소를 추가하려고 하면 다음에 있는 모든 원소는 공간을 만들기 위해 이동해야한다.
	// 원소를 많이 삭제해도 배열의 크기가 줄어들지 않는다.
	// 원소의 개수가 계속 변경되는 리스트라면 LinkedList 클래스로 배열을 생성하는 것이 더 적합하다.
	// 앞,뒤 원소의 위치를 가르키기 때문에 중간 삽입,삭제에 용이	
	@SuppressWarnings("unused")
	public static class SimpleLinekdList<E> {
		// Element 클래스의 내부에는 이전,다음 원소의 위치를 가르키는 재귀타입의 next라는 필드가 있다.
		private static class Element<E> {
			E value;
			Element<E> head;
			Element<E> next;
		}
	}
	
	
	@Test
	public void queueInsertion() {
		// 선입선출
		// peek() : 최선입 원소 반환
		// remove() : 최선입 원소 반환 및 삭제
		
		Queue<String> queue = new LinkedList<String>();
		queue.add("first");
		queue.add("second");
		queue.add("third");
		
		assertEquals("first", queue.remove());
		assertEquals("second", queue.remove());
		assertEquals("third", queue.peek());
		assertEquals("third", queue.remove());
	}
}
