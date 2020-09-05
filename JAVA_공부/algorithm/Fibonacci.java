package interviews.java.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 1~n까지의 피보나치 수열 반환
public class Fibonacci {

	public static List<Integer> fibonacci(int n) {
		
		// 예외처리
		if (n<0) {
			throw new IllegalArgumentException("n must not be less than zero");
		}
		if (n==0) {
			return new ArrayList<Integer>();
		}
		if (n==1) {
			return Arrays.asList(1);
		}
		if (n==2) {
			return Arrays.asList(1, 1);
		}
		
		
		// 피보나치 처리
		final List<Integer> seq = new ArrayList<Integer>(n);
		seq.add(1);
		n = n-1;
		seq.add(1);
		n = n-1;
		
		while (n>0) {
			int a = seq.get(seq.size()-1);
			int b = seq.get(seq.size()-2);
			seq.add(a+b);
			n = n-1;
		}
		
		return seq;
	}
	
	// 피보나치수열의 n번째 값을 호출
	public static int fibN(int n) {
		if (n<0) {
			throw new IllegalArgumentException("n must not be less than zero");
		}
		if (n==0) {
			return 0;
		}
		if (n==1) {
			return 1;
		}
		return (fibN(n-1) + fibN(n-2));
	}
	
	
	/*  엄청 비효율적
	 	예를 들어 45번째 값을 계산하려면 43번재 44번째, 또 42,41 + 43,42 ...
	 	실행할 때마다 매우 많은 메서드를 불러와야 하는 악순환의 연속.
	 	
	 	문제해결을 위해 모든 계산 결과를 캐시함으로써 알 수 없는 피보나치 수만 연산하게 하면 된다.
	 */
	private Map<Integer, Integer> fibCache = new HashMap<Integer, Integer>();
	
	
	public int cachedFibN(int n) {
		if (n<0) {
			throw new IllegalArgumentException("n must not be less than zero");
		}
		fibCache.put(0, 0);
		fibCache.put(1, 1);
		
		return recursiveCachedFibN(n);
	}
	
	
	private int recursiveCachedFibN(int n) {
		if(fibCache.containsKey(n)) {
			return fibCache.get(n);
		}
		
		int value = recursiveCachedFibN(n-1) + recursiveCachedFibN(n-2);
		fibCache.put(n, value);
		return value;
	}
	
	
	// 확인
	public static void main(String[] args) {
		for(Integer integer : fibonacci(10)) {
			System.out.print(integer+" ");
		}
		System.out.println();
		
		// 캐시맵을 구현한것과 안한 것의 속도차이를 볼 수 있다.
		final long start = System.nanoTime();
		System.out.println(fibN(20));
		final long end = System.nanoTime();
		System.out.println("nonCachedFibN : " + (end-start));
		
		final long start2 = System.nanoTime();
		System.out.println(new Fibonacci().cachedFibN(20));
		final long end2 = System.nanoTime();
		System.out.println("cachedFibN : " + (end2-start2));
	}
	
	
}
