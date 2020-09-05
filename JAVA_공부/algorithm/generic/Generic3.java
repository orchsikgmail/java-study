package interviews.java.algorithm.generic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Generic2�� Integer��ü������ �۵��Ѵ�.
// �׷� ������ Ÿ���� ����Ʈ���� �۵��ϰ� �ٸ� Ÿ���� ����Ʈ�� ��ȯ�ϴ� �� ��� �ؾ��ұ�?
public class Generic3 {

	// ����
	public static void main(String[] args) {
		Generic3 g3 = new Generic3();
		g3.stringLengths();
	}


	// �ٸ� Ÿ������ ���� �����ϵ��� �����ϱ� ���� ���׸��� ����Ѵ�.
	public interface GenericOperation<A,B> {
		B performOperation(A value);
	}

	// Ư��Ÿ���� ����Ʈ�� �ٸ� Ÿ���� ����Ʈ�� �����ϴ� �޼ҵ�
	public <A,B> List<B> mapList(List<A> values, GenericOperation<A, B> op) {
		List<B> toReturn = new ArrayList<B>(values.size());
		for(A a : values) {
			toReturn.add(op.performOperation(a));
		}
		return toReturn;
	}

	// �������̽� ������ ����, �Է¹��� ���ڿ��� ���̸� ������ ���� �ϴ� �޼ҵ�
	public class StringLengthOperation implements GenericOperation<String, Integer> {
		@Override
		public Integer performOperation(String value) {
			return value.length();
		}
	}

	// �׽�Ʈ �޼ҵ�
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
