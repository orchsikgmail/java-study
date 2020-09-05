package interviews.java.sortAlgorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// �������� ���������̳� �������� ���� ȿ������ ������ ����
// pivot�� ù��° ���ҷ� ����ϴ� ���, �������� ���ĵ� ����Ʈ�� �����Ҷ� �־�.
// ��ʹܰ迡�� ���� �� ����Ʈ�� �и��ϴ� �ð��� ���� �� �ִ�.
public class Quick {

	public static List<Integer> quicksort(List<Integer> numbers){
		if(numbers.size()<2) {
			return numbers;
		}
		
		
		Integer pivot = numbers.get(0);	
		final List<Integer> lower = new ArrayList<Integer>();
		final List<Integer> higher = new ArrayList<Integer>();
		
		
		// pivot�� �������� �� �����׷�� �� ū �׷����� ������.
		for(int i=1; i<numbers.size(); i++) {
			if(numbers.get(i) < pivot) {
				lower.add(numbers.get(i));
			} else {
				higher.add(numbers.get(i));
			}
		}
		
		
		// ���� �ݺ����� ���� ������ �ൿ�� ���ȣ��� �ݺ���Ų��. numbers.size()<2�����ǿ� ���� ���ᰡ��.
		// �� �׷��� �� �� �׷����� ������ �ᱹ ���� �������� �۰� ũ�� ��������.
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
