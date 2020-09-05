package interviews.java.collectionFramework;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MyTree {

	@SuppressWarnings({ "rawtypes" , "unchecked" })
	public class SimpleTree<E extends Comparable>{
		// SimpleTreeŬ������ �ڽ����� ���� �� �ٸ� ���Ÿ��.
		// Comparable �������̽� Ÿ���� ���Ҹ� ������ �� �ִ�, �˻��޼ҵ� ������ ���ؼ�.
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
			
			// toFind.compareTo(value) < 0 : �����(�ش� �����  ��) ���� ã�����°���  ������
			// ���� Ʈ���� ���� ã�ƺ���
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
