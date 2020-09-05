package interviews.java.pattern.templateMethod;

import java.util.LinkedList;

public class MyStack {
	
	private final LinkedList<Integer> stack;
	public MyStack() {
		stack = new LinkedList<Integer>();
	}
	public MyStack(final LinkedList<Integer> initialState) {
		stack = initialState;
	}
	
	
	
	public void push(final int number) {
		stack.add(0, number);
	}

	
	public int pop() {
		return stack.remove(0);
	}

	
	public MyStack filter(final StackPredicate filter) {
		final LinkedList<Integer> initialState = new LinkedList<Integer>();
		for(int integer : stack) {
			if(filter.isValid(integer)) {
				initialState.add(integer);
			}
		}
		return new MyStack(initialState);
	}
	
	
}


