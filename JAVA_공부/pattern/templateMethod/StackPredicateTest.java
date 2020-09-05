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
		// 링크드리스트 형태의 1~10의 원소가지고 있는 stack객체.
		// 해당 객체의 filter메소드를 이용, 매개변수로 필터객체를 주는데 재정의 해서 사용가능.
		
		// 짝수만 나오게 재정의
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
		// 정수만 나오게 재정의
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
