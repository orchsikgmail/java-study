package interviews.java.algorithm.generic;

import java.util.ArrayList;
import java.util.List;

// 1�� ���ϴ� ���� �ƴ϶� ���ϴ� ������ ������ �纻�� ����� �޼ҵ带 �ۼ�.
// Ŭ���̾�Ʈ����  �������̽� �����ϸ� �ȴ�.
public class Generic2 {
	
	// ����
	public static void main(String[] args) {
		// 10�� �߰����ִ� �������̽� 
		IntegerOperation op = new IntegerOperation() {
			@Override
			public int perforomOperation(int value) {
				return value + 10;
			}
		};
		
		// �������̽��� �ִ� �Ű����� ���ٽ� ���, ���� �������̽� ��ü �̻��.
		int[] numbers = {1,2,3,4,5};
		List<Integer> toReturn = Generic2.updateList(numbers, (value) -> {
			return value+1;
		});
		
		for(int new_number : toReturn) {
			System.out.print(new_number+" ");
		}
	}
	
	
	// �������̽� ����ؼ� ���ο� ����Ʈ ��� �޼ҵ�
	public static List<Integer> updateList(int[] numbers, IntegerOperation op){
		
		ArrayList<Integer> toReturn = new ArrayList<Integer>(numbers.length);
	
		for(int number : numbers) {
			toReturn.add(op.perforomOperation(number));
		}
		
		return toReturn;
	}
	
	// ������ �޾� ������ �����ϴ� �������̽�
		public interface IntegerOperation{
			int perforomOperation(int value);
		}
	
}
