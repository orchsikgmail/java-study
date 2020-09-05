package interviews.java.sortAlgorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 병합정렬 
// 퀵정렬의 단점을 보완
// 정렬을 재귀호출에서 분리시키고 리스트를 분리시키는 행동만 재귀호출로 처리
public class Merge {
	public static List<Integer> mergesort(final List<Integer> values) {
		if (values.size() < 2) {
			return values;
		}

		// subList(formIndex, toIndex) : fromIndex ~ toIndex-1 까지 리스트를 잘라줌
		final List<Integer> leftHalf = values.subList(0, values.size() / 2);
		final List<Integer> righttHalf = values.subList(values.size() / 2, values.size());
		
		
		return merge(mergesort(leftHalf), mergesort(righttHalf));
	}

	
	private static List<Integer> merge(
			final List<Integer> left, 
			final List<Integer> right) {
		
		final List<Integer> merged = new ArrayList<Integer>(left.size() + right.size());

		
		int leftPtr = 0;
		int rightPtr = 0;
		while (leftPtr < left.size() && rightPtr < right.size()) {
			if (left.get(leftPtr) < right.get(rightPtr)) {
				merged.add(left.get(leftPtr));
				leftPtr++;
			} else {
				merged.add(right.get(rightPtr));
				rightPtr++;
			}
		}

		
		while (leftPtr < left.size()) {
			merged.add(left.get(leftPtr));
			leftPtr++;
		}
		while (rightPtr < right.size()) {
			merged.add(right.get(rightPtr));
			rightPtr++;
		}

		
		return merged;
	}

	
	public static void main(String[] args) {
		List<Integer> numbers = new ArrayList<Integer>(Arrays.asList(3, 2, 1));

		List<Integer> sortedList = mergesort(numbers);

		for(int i=0; i<sortedList.size(); i++) {
			System.out.print(sortedList.get(i)+" "); 
		}
	}
}
