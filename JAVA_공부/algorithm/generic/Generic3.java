package interviews.java.algorithm.generic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Generic2는 Integer객체에서만 작동한다.
// 그럼 임의의 타입의 리스트에서 작동하고 다른 타입의 리스트를 반환하는 건 어떻게 해야할까?
public class Generic3 {

	// 실행
	public static void main(String[] args) {
		Generic3 g3 = new Generic3();
		g3.stringLengths();
	}


	// 다른 타입으로 값을 전달하도록 매핑하기 위해 제네릭을 사용한다.
	public interface GenericOperation<A,B> {
		B performOperation(A value);
	}

	// 특정타입의 리스트를 다른 타이의 리스트로 매핑하는 메소드
	public <A,B> List<B> mapList(List<A> values, GenericOperation<A, B> op) {
		List<B> toReturn = new ArrayList<B>(values.size());
		for(A a : values) {
			toReturn.add(op.performOperation(a));
		}
		return toReturn;
	}

	// 인터페이스 구현을 통해, 입력받은 문자열의 길이를 정수로 리턴 하는 메소드
	public class StringLengthOperation implements GenericOperation<String, Integer> {
		@Override
		public Integer performOperation(String value) {
			return value.length();
		}
	}

	// 테스트 메소드
	public void stringLengths() {
		List<String> strings = Arrays.asList("acting","the","java","interview");
		List<Integer> expected = Arrays.asList(6,3,4,9);
		List<Integer> actual = mapList(strings, new StringLengthOperation());

		for(int n : actual) {
			System.out.print(n+ " " );
		}
		System.out.println();
		for(int n : expected) {
			System.out.print(n+ " " );
		}
	}


}
