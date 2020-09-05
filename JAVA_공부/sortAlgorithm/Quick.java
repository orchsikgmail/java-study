package interviews.java.sortAlgorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 퀵정렬은 버블정렬이나 삽입정렬 보다 효율적인 성능을 발휘
// pivot을 첫번째 원소로 사용하는 경우, 역순으로 정렬된 리스트를 정렬할때 최악.
// 재귀단계에서 단지 각 리스트로 분리하는 시간만 줄일 수 있다.
public class Quick {

	public static List<Integer> quicksort(List<Integer> numbers){
		if(numbers.size()<2) {
			return numbers;
		}
		
		
		Integer pivot = numbers.get(0);	
		final List<Integer> lower = new ArrayList<Integer>();
		final List<Integer> higher = new ArrayList<Integer>();
		
		
		// pivot을 기준으로 더 작은그룹과 더 큰 그룹으로 나눈다.
		for(int i=1; i<numbers.size(); i++) {
			if(numbers.get(i) < pivot) {
				lower.add(numbers.get(i));
			} else {
				higher.add(numbers.get(i));
			}
		}
		
		
		// 위의 반복문을 통한 나누는 행동을 재귀호출로 반복시킨다. numbers.size()<2의조건에 의해 종료가능.
		// 각 그룹을 또 각 그룹으로 나눠서 결국 원소 단위별로 작고 크고가 나눠진다.
		final List<Integer> sorted = quicksort(lower);
		sorted.add(pivot);
		sorted.addAll(quicksort(higher));

		
		return sorted;
	}
	
	
	public static void main(String[] args) {
		List<Integer> numbers = new ArrayList<Integer>(
				Arrays.asList(5,4,2,3,1)
				);
		
		
		List<Integer> sortedList = quicksort(numbers);
				
		
		for(int i=0; i<sortedList.size(); i++) {
			System.out.print(sortedList.get(i)+" ");
		}
	}
}
