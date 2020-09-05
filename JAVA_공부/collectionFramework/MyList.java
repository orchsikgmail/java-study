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
		final int[] integers = new int[3]; // ����� ũ�� ����
		final boolean[] booleans = {true, false, false, true}; // �Ͻ��� ũ�� ����
		final String[] strings = new String[] {"aaa", "bbbb"};
		
		final Random r = new Random();
		final String[] randomArrayLength = new String[r.nextInt(100)];
	}
	
	
	@Test
	public void arrayCopy() {
		// �迭 ��ü�� ������� ��, ũ�Ⱑ ū �迭�� ����� ���� �迭�� ������ �ٿ��־��ش�.
		// �����迭�� ū�迭�� �ּҰ��� �־ ū�迭�� ����Ҽ��ְ��Ѵ�.
		int[] integers = {1,2,3};
		int[] newIntegers = new int[5];
		
		System.arraycopy(integers, 0, newIntegers, 0, integers.length);
		
		integers = newIntegers;
		integers[4] = 4;
		
		assertEquals(4, integers[4]);
	}
	
	// ArrayList Ŭ������ ����Ʈ�� �����ͷ� �迭�� ����ϴ� List �������̽��� ����ü��.
	// ������ġ�� �߰���ġ�� ���ο� ���Ҹ� �߰��Ϸ��� �ϸ� ������ �ִ� ��� ���Ҵ� ������ ����� ���� �̵��ؾ��Ѵ�.
	// ���Ҹ� ���� �����ص� �迭�� ũ�Ⱑ �پ���� �ʴ´�.
	// ������ ������ ��� ����Ǵ� ����Ʈ��� LinkedList Ŭ������ �迭�� �����ϴ� ���� �� �����ϴ�.
	// ��,�� ������ ��ġ�� ����Ű�� ������ �߰� ����,������ ����	
	@SuppressWarnings("unused")
	public static class SimpleLinekdList<E> {
		// Element Ŭ������ ���ο��� ����,���� ������ ��ġ�� ����Ű�� ���Ÿ���� next��� �ʵ尡 �ִ�.
		private static class Element<E> {
			E value;
			Element<E> head;
			Element<E> next;
		}
	}
	
	
	@Test
	public void queueInsertion() {
		// ���Լ���
		// peek() : �ּ��� ���� ��ȯ
		// remove() : �ּ��� ���� ��ȯ �� ����
		
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
