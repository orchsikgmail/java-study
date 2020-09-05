package interviews.java.algorithm;

import java.util.ArrayList;
import java.util.List;

import org.junit.platform.commons.util.StringUtils;

// 1~n���� ���ڸ� ����ض�
// 3�� ����� Fizz ���ڿ� ���, 5�ǹ���� Buzz ���ڿ� ���
public class FizzBuzz {
	
	public static List<String> fizzBuzz(final int n){
		
		final List<String> toReturn = new ArrayList<String>(n);
		for(int i=1; i<=n; i++) {
			// 15�� ����� 3,5�� ����� �ɷ��� �� �ɷ��� �� �����Ƿ� 15�� ��� ������ ���� �ɾ�����Ѵ�.
			if(i%15 ==0) {
				toReturn.add("FizzBuzz");
			} else if(i%3 ==0) {
				toReturn.add("Fizz");
			} else if(i%5 ==0) {
				toReturn.add("Buzz");
			} else {
				toReturn.add(Integer.toString(i));
			}
		}
		
		return toReturn;
	}

	
	// �� �˰����� �Ϻ��� ���� �ƴϴ�.
	// �ڵ��� ���뼺�� ���� �߻�ȭ�� ����� ó������ �ʾҴ�. Fizz,Buzz,FizzBuzz ������ �߻�ȭ�ؼ� ��������.
	// �׷��� ���� ������ �׽�Ʈ�� �����ϰ�, ���ϴ´�� �ٸ� �ܾ ��ȯ�ϰԲ� ��������.
	public static List<String> alternativeFizzBuzz(final int n){
		
		final List<String> toReturn = new ArrayList<String>(n);
		
		for(int i=0; i<=n; i++) {
			final String word = toWord(3, i, "Fizz") + toWord(5, i, "Buzz");
			
			if(StringUtils.isBlank(word)) {
				toReturn.add(Integer.toString(i));
			} else {
				toReturn.add(word);
			}
		}
		
		return toReturn;
	}
	
	
	private static String toWord(final int divisor, final int value, final String word) {
		return value%divisor==0 ? word : "";
	}
	
	
	// Ȯ��
	public static void main(String[] args) {
		List<String> toReturn = fizzBuzz(30);
		int index =0;
		for(String s : toReturn) {
			System.out.print(s+" ");
			
			index++;
			if(index%15==0) {
				System.out.println();
			}
		}
	}
}

