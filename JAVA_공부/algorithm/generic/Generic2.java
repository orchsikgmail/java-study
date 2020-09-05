package interviews.java.algorithm.generic;

import java.util.ArrayList;
import java.util.List;

// 1을 더하는 것이 아니라 원하는 연산을 실행한 사본을 만드는 메소드를 작성.
// 클라이언트에게  인터페이스 제공하면 된다.
public class Generic2 {
	
	// 실행
	public static void main(String[] args) {
		// 10을 추가해주는 인터페이스 
		IntegerOperation op = new IntegerOperation() {
			@Override
			public int perforomOperation(int value) {
				return value + 10;
			}
		};
		
		// 인터페이스를 주는 매개변수 람다식 사용, 위의 인터페이스 객체 미사용.
		int[] numbers = {1,2,3,4,5};
		List<Integer> toReturn = Generic2.updateList(numbers, (value) -> {
			return value+1;
		});
		
		for(int new_number : toReturn) {
			System.out.print(new_number+" ");
		}
	}
	
	
	// 인터페이스 사용해서 새로운 리스트 얻는 메소드
	public static List<Integer> updateList(int[] numbers, IntegerOperation op){
		
		ArrayList<Integer> toReturn = new ArrayList<Integer>(numbers.length);
	
		for(int number : numbers) {
			toReturn.add(op.perforomOperation(number));
		}
		
		return toReturn;
	}
	
	// 정수를 받아 정수를 리턴하는 인터페이스
		public interface IntegerOperation{
			int perforomOperation(int value);
		}
	
}
