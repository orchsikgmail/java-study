package interviews.java.sortAlgorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
/*
Given a list l, and a new list nl
 for each element originalListElem in list l:
  for each element newListElem in list nl:
   if(originalListElem < newListElem):
    insert originalListElem in nl before newListElem
   else move to the next element
 if originalListElem has not been inserted:
  insert at end of nl
  
# ���ο� ����Ʈ���� �����ϰ� �ش� ����Ʈ�� ��ȯ
# LinkedList���, �߰��� ���Ҹ� �߰��ϴµ� �ſ� ȿ����
# ���Ұ� ���������� ���ԵǸ� �ٱ��� �ݺ������� ���� ȿ����
# �̹� ������ �Ǿ��ִ� ���, �־���ȿ��
 */
class Insert {

	public static List<Integer> insertSort(final List<Integer> numbers){
		// final List<Integer> sortedList ���???
		// sortedList = createList() �� ���� ���� ����Ʈ��ü ���� �Ұ�
		// sortedList��� List�� value�� �߰����� ����
		final List<Integer> sortedList = new LinkedList<Integer>();
		
		// �ݺ����� �̸��ְ� conitnue originalList ����Ѵ�.
		originalList : for (Integer number : numbers) {
			for (int i=0; i<sortedList.size(); i++) {
				if(number < sortedList.get(i)) {
					sortedList.add(i, number);
					continue originalList;
				}
			}
			sortedList.add(sortedList.size(), number); 
		}
		return sortedList;   
	}
	
	public static void main(String[] args) {
		List<Integer> numbers = new ArrayList<Integer>(
				Arrays.asList(3,4,1,2,5)
				);
				
		List<Integer> sortedList = insertSort(numbers);
		for(int i=0; i<sortedList.size(); i++) {
			System.out.println(sortedList.get(i));
		}
	}
	
}
