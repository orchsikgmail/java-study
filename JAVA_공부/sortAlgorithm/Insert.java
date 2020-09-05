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
  
# 새로운 리스트에를 생성하고 해당 리스트를 반환
# LinkedList사용, 중간에 원소를 추가하는데 매우 효율적
# 원소가 성공적으로 삽입되면 바깥족 반복문으로 빠져 효율적
# 이미 정렬이 되어있는 경우, 최악의효울
 */
class Insert {

	public static List<Integer> insertSort(final List<Integer> numbers){
		// final List<Integer> sortedList 사용???
		// sortedList = createList() 와 같이 새로 리스트객체 생성 불가
		// sortedList라는 List에 value를 추가삭제 가능
		final List<Integer> sortedList = new LinkedList<Integer>();
		
		// 반복문에 이름주고 conitnue originalList 사용한다.
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
