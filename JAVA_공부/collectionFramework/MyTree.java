package interviews.java.collectionFramework;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MyTree {

	@SuppressWarnings({ "rawtypes" , "unchecked" })
	public class SimpleTree<E extends Comparable>{
		// SimpleTree클래스를 자식으로 갖는 또 다른 재귀타입.
		// Comparable 인터페이스 타입의 원소를 저장할 수 있다, 검색메소드 구현을 위해서.
		private E value;
		private SimpleTree<E> left;
		private SimpleTree<E> right;
		public E getValue() {
			return value;
		}
		public void setValue(E value) {
			this.value = value;
		}
		public SimpleTree<E> getLeft() {
			return left;
		}
		public void setLeft(SimpleTree<E> left) {
			this.left = left;
		}
		public SimpleTree<E> getRight() {
			return right;
		}
		public void setRight(SimpleTree<E> right) {
			this.right = right;
		}
		public SimpleTree() {
		}
		public SimpleTree(E value, SimpleTree<E> left, SimpleTree<E> right) {
			this.value = value;
			this.left = left;
			this.right = right;
		}

		
		public boolean search(final E toFind) {
			if(toFind.equals(value)) {
				return true;
			}
			
			// toFind.compareTo(value) < 0 : 밸류값(해당 노드의  값) 보다 찾으려는값이  작으면
			// 왼쪽 트리에 가서 찾아봐라
			if(toFind.compareTo(value) < 0 && left!=null) {
				return left.search(toFind);
			} 
			return right!=null && right.search(toFind);
		}
		
		
		
		public void insert(final E toInsert) {
			if(toInsert.compareTo(value)<0) {
				if(left==null) {
					left = new SimpleTree<E>(toInsert, null, null);
				} else {
					left.insert(toInsert);
				}
			} else {
				if(right==null) {
					right = new SimpleTree<E>(toInsert, left, right);
				} else {
					right.insert(toInsert);
				}
			}
		}
	}
	
	@Test
	public void create() {
		SimpleTree<Integer> root = new SimpleTree<Integer>(7, null, null);
		root.insert(3);
		root.insert(9);
		root.insert(10);

		assertTrue(root.search(10));
		assertEquals(Integer.valueOf(10), root.getRight().getRight().getValue());
		/*
				  7
			  3       9
			n   n   n   10
		*/
	}
}
