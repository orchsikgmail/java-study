package interviews.java.collectionFramework.stack;

public class MyStackImpl<E> implements MyStack<E> {

	private int index;
	private Object[] stackDatas;

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public MyStackImpl (int size){
		stackDatas = new Object[size];
		index = 0;
	}

	@Override
	public void push(E data) {
		if(index>=stackDatas.length) {
			throw new IndexOutOfBoundsException("stack is full");
		} else {
			stackDatas[index++]= data;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public E peek() {
		if(isEmpty()) {
			throw new IndexOutOfBoundsException("stack is empty");
		} else {
			return (E)stackDatas[index-1];
		}
		
	}

	@Override
	public E pop() {
		if(isEmpty()) {
			throw new IndexOutOfBoundsException("stack is empty");
		} else {
			E popresult = peek();
			index--;
			return popresult;
		}
		
	}

	@Override
	public boolean isEmpty() {
		return index<=0;
	}

}
