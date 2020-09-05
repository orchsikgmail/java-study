package interviews.java.sortAlgorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 졍렬된 리스트의 속성을 이용한다는 장점이 있다.
// 즉, 많은 원소가 주어진 원소와 맞지 않다는 걸 이미 알고 사용하는 것이므로,
// 원소를 일일이 비교하지않고도 주어진 원소를 찾을 수 있다.
public class BinarySearch {

	public static boolean binarySearch(
			final List<Integer> numbers,
			final Integer value
			) {
	
		if(numbers==null || numbers.isEmpty()) {
			return false;
		}
		
		
		final Integer comparison = numbers.get(numbers.size()/2);
		if(value.equals(comparison)) {
			return true;
		}
		
		
		if(value<comparison) {
			return binarySearch(numbers.subList(0, numbers.size()/2), value);
		} else {
			return binarySearch(numbers.subList(numbers.size()/2+1, numbers.size()), value);
		}
	}
	
	
	public static void main(String[] args) {
		List<Integer> numbers = new ArrayList<Integer>(
				Arrays.asList(6,4,5,3,2,1,8,7,10,9)
				);
		
		numbers = Merge.mergesort(numbers);

		boolean is2 = binarySearch(numbers, 2);
		boolean is11 = binarySearch(numbers, 11);
		
		System.out.println(is2+" - "+is11);
	}
}
