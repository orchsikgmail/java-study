package interviews.java.compare;

import static org.junit.Assert.assertArrayEquals;

import java.util.Arrays;
import java.util.Comparator;

import org.junit.jupiter.api.Test;

class Code {
	/*
	Comparable과 Comparator 인터페이스의 차이는?
	Comparable 인터페이스는 객체에게 한 가지 기본 정렬 규칙을 주고싶을 때 사용
	Comprator를 구현한 객체는 정렬기준 자체다.기본정렬 외에 원하는대로 정렬 순서를 지정하고 싶은 곳에 사용.

	Array와 Collection 클래스는 몇 가지 오버로딩된 정렬 메소드가 있다.
	크게 배열을 매개변수로 받는 메서드와 
	배열과 Comparator 객체를 매개변수로 받는 메서드의 두가지로 분류된다.

	객체 배열을 정렬하려면 String과 같이  Comparable 인터페이스를 구현해야하다.
	 */


	// 숫자 순서대로 정렬
	@Test
	public void sortInts() {
		final int[] numbers = { -3,-5,1,4,2 };
		final int[] expected = { -5,-3,1,2,4 };

		Arrays.sort(numbers);
		assertArrayEquals(numbers, expected);
	}

	
	// 객체 순서대로 정렬
	@Test
	public void sortObjects() {
		final String[] strings = { "c", "b", "a" };
		final String[] expected = { "a", "b", "c" };

		Arrays.sort(strings);
		assertArrayEquals(strings, expected);
	}
	
	
	// Comparator 사용해 정렬방법 지정
	public class ReverseNumericalOrder implements Comparator<Integer>{
		@Override
		public int compare(Integer o1, Integer o2) {
			return o2 - o1;
		}
	}
	
	@Test
	public void reverseSortInts() {
		final Integer[] ints = { 1, 2, 3 };
		final Integer[] expected = { 3, 2, 1 };
		
		Arrays.sort(ints, new ReverseNumericalOrder());
		assertArrayEquals(ints, expected);
	}









}