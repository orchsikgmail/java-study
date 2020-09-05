package interviews.java.algorithm;

import java.util.ArrayList;
import java.util.List;

import org.junit.platform.commons.util.StringUtils;

// 1~n까지 숫자를 출력해라
// 3의 배수는 Fizz 문자열 출력, 5의배수는 Buzz 문자열 출력
public class FizzBuzz {
	
	public static List<String> fizzBuzz(final int n){
		
		final List<String> toReturn = new ArrayList<String>(n);
		for(int i=1; i<=n; i++) {
			// 15의 배수는 3,5의 배수를 걸러낼 때 걸러질 수 있으므로 15의 배수 조건을 먼저 걸어줘야한다.
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

	
	// 위 알고리즘은 완벽한 답이 아니다.
	// 코드의 재사용성과 논리적 추상화가 제대로 처리되지 않았다. Fizz,Buzz,FizzBuzz 각각을 추상화해서 제공하자.
	// 그래야 각각 독립적 테스트가 가능하고, 원하는대로 다른 단어를 반환하게끔 수정가능.
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
	
	
	// 확인
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

