package interviews.java.sortAlgorithm;

/*
 for i between 0 and (array length -2):
 if(array(i+1) < array(i)){
 	switch array(i) and array(i+1)
 }
 repeat until a complete iteration where no elements are switched
 
 # ��������, ��ȿ���� , 
 # �������� ���ĵ��ִ� ��� �־� <= 1��ȯ��1���Ҹ�����
 */

public class Bubble {
	
	public void bubbleSort(int[] numbers) {
		boolean numbersSwitched;
		
		do {
			numbersSwitched = false;
			for(int i=0; i<numbers.length-1; i++) {
				if(numbers[i+1] < numbers[i]) {
					int tmp = numbers[i+1];
					numbers[i+1] = numbers[i];
					numbers[i] = tmp;
					numbersSwitched = true;
				}
			}
		} while (numbersSwitched);
	}
	
	public static void main(String[] args) {
		Bubble b = new Bubble();
		
		int[] numbers = new int[] {5,2,4,1,9};
		b.bubbleSort(numbers);
		
		for(int i=0; i<numbers.length; i++) {
			System.out.print(numbers[i]+" ");
		}
	}

}
