package interviews.java.compare;

import static org.junit.Assert.assertArrayEquals;

import java.util.Arrays;
import java.util.Comparator;

import org.junit.jupiter.api.Test;

class Code {
	/*
	Comparable�� Comparator �������̽��� ���̴�?
	Comparable �������̽��� ��ü���� �� ���� �⺻ ���� ��Ģ�� �ְ���� �� ���
	Comprator�� ������ ��ü�� ���ı��� ��ü��.�⺻���� �ܿ� ���ϴ´�� ���� ������ �����ϰ� ���� ���� ���.

	Array�� Collection Ŭ������ �� ���� �����ε��� ���� �޼ҵ尡 �ִ�.
	ũ�� �迭�� �Ű������� �޴� �޼���� 
	�迭�� Comparator ��ü�� �Ű������� �޴� �޼����� �ΰ����� �з��ȴ�.

	��ü �迭�� �����Ϸ��� String�� ����  Comparable �������̽��� �����ؾ��ϴ�.
	 */


	// ���� ������� ����
	@Test
	public void sortInts() {
		final int[] numbers = { -3,-5,1,4,2 };
		final int[] expected = { -5,-3,1,2,4 };

		Arrays.sort(numbers);
		assertArrayEquals(numbers, expected);
	}

	
	// ��ü ������� ����
	@Test
	public void sortObjects() {
		final String[] strings = { "c", "b", "a" };
		final String[] expected = { "a", "b", "c" };

		Arrays.sort(strings);
		assertArrayEquals(strings, expected);
	}
	
	
	// Comparator ����� ���Ĺ�� ����
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