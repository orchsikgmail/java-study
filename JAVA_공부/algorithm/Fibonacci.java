package interviews.java.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 1~n������ �Ǻ���ġ ���� ��ȯ
public class Fibonacci {

	public static List<Integer> fibonacci(int n) {
		
		// ����ó��
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
		
		
		// �Ǻ���ġ ó��
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
	
	// �Ǻ���ġ������ n��° ���� ȣ��
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
	
	
	/*  ��û ��ȿ����
	 	���� ��� 45��° ���� ����Ϸ��� 43���� 44��°, �� 42,41 + 43,42 ...
	 	������ ������ �ſ� ���� �޼��带 �ҷ��;� �ϴ� �Ǽ�ȯ�� ����.
	 	
	 	�����ذ��� ���� ��� ��� ����� ĳ�������ν� �� �� ���� �Ǻ���ġ ���� �����ϰ� �ϸ� �ȴ�.
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
	
	
	// Ȯ��
	public static void main(String[] args) {
		for(Integer integer : fibonacci(10)) {
			System.out.print(integer+" ");
		}
		System.out.println();
		
		// ĳ�ø��� �����ѰͰ� ���� ���� �ӵ����̸� �� �� �ִ�.
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
