package interviews.java.algorithm.generic;

import java.util.ArrayList;

public class Generic {

	public static void main(String[] args) {
		Generic generic  = new Generic();
		int[] numbers = {9,99,999};
		
		for(int n : generic.addOne(numbers)) {
			System.out.print(n+" ");
		}
	}
	
	
	// ����Ʈ�� �� ���ҿ� 1�� ���� �纻�� ����� �޼ҵ带 �ۼ��ض�.
	final ArrayList<Integer> addOne(int[] numbers){
		
		final ArrayList<Integer> toReturn  = new ArrayList<Integer>(numbers.length);
		
		for(final int number : numbers) {
			toReturn.add(number+1);
		}
		
		return toReturn;
	}
	
	
}
