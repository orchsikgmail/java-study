package interviews.java.collectionFramework.stack;

public class StackApp {

	
	public static void main(String[] args) {
		MyStack<Integer> mystack = new MyStackImpl<Integer>(10);
		System.out.println("������� �����?"+mystack.isEmpty());
		
		mystack.push(1);
		System.out.println("1�־��� �����?"+mystack.isEmpty());
		System.out.println("�������� �����=�� �غ� =>"+mystack.peek());
		System.out.println("��� ���ߴµ� �����?"+mystack.isEmpty());
		System.out.println("���غ� =>"+mystack.pop());
		System.out.println("�����?"+mystack.isEmpty());
	}
}
