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
	
	
	// 리스트의 각 원소에 1을 더한 사본을 만드는 메소드를 작성해라.
	final ArrayList<Integer> addOne(int[] numbers){
		
		final ArrayList<Integer> toReturn  = new ArrayList<Integer>(numbers.length);
		
		for(final int number : numbers) {
			toReturn.add(number+1);
		}
		
		return toReturn;
	}
	
	
}
