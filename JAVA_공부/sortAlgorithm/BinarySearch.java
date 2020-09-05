package interviews.java.sortAlgorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// ���ĵ� ����Ʈ�� �Ӽ��� �̿��Ѵٴ� ������ �ִ�.
// ��, ���� ���Ұ� �־��� ���ҿ� ���� �ʴٴ� �� �̹� �˰� ����ϴ� ���̹Ƿ�,
// ���Ҹ� ������ �������ʰ� �־��� ���Ҹ� ã�� �� �ִ�.
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
