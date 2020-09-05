package interviews.java.algorithm;


class Factorial {
	
	public static long factorial(int n) {
		if (n<1) {
			throw new IllegalArgumentException("n must not be greater than zero");
		}
		
		if (n==1) {
			return 1;
		}
		
		return n * factorial(n-1);
	}
	
	//재귀적 방법을 사용하지 않은 팩토리 구현 작성.
	public static long factorial2(int n) {
		if (n<1) {
			throw new IllegalArgumentException("n must not be greater than zero");
		}
		
		long toReturn = 1;
		for(int i=1; i<=n; i++) {
			toReturn *= i;
		}
		
		return toReturn;
	}
	
	
	public static void main(String[] args) {
		System.out.println(factorial(10));
		System.out.println(factorial2(10));
	}
	
}
