package interviews.java.collectionFramework.stack;

public class StackApp {

	
	public static void main(String[] args) {
		MyStack<Integer> mystack = new MyStackImpl<Integer>(10);
		System.out.println("만들었어 비었니?"+mystack.isEmpty());
		
		mystack.push(1);
		System.out.println("1넣었어 비었니?"+mystack.isEmpty());
		System.out.println("맨위스택 집어봐=픽 해봐 =>"+mystack.peek());
		System.out.println("방금 픽했는데 비었니?"+mystack.isEmpty());
		System.out.println("팝해봐 =>"+mystack.pop());
		System.out.println("비었니?"+mystack.isEmpty());
	}
}
