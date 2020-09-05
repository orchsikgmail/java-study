package interviews.java.pattern.templateMethod;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StackPredicateTest {

	private MyStack stack;
	
	@BeforeEach
	public void createStack() {
		stack = new MyStack();
		for(int i=0; i<=10; i++) {
			stack.push(i);
		}
	}
	
	@Test
	public void evenPredicate() {
		// ��ũ�帮��Ʈ ������ 1~10�� ���Ұ����� �ִ� stack��ü.
		// �ش� ��ü�� filter�޼ҵ带 �̿�, �Ű������� ���Ͱ�ü�� �ִµ� ������ �ؼ� ��밡��.
		
		// ¦���� ������ ������
		final MyStack filtered = stack.filter(new StackPredicate() {
			@Override
			public boolean isValid(int i) {
				return (i%2 == 0);
			}
		});
		
		assertEquals(Integer.valueOf(10), filtered.pop());
		assertEquals(Integer.valueOf(8), filtered.pop());
		assertEquals(Integer.valueOf(6), filtered.pop());
	}
	
	@Test
	public void allPredicate() {
		// ������ ������ ������
		final MyStack filtered = stack.filter(new StackPredicate() {
			@Override
			public boolean isValid(int i) {
				return true;
			}
		});
		
		assertEquals(Integer.valueOf(10), filtered.pop());
		assertEquals(Integer.valueOf(9), filtered.pop());
		assertEquals(Integer.valueOf(8), filtered.pop());
	}
	

}
