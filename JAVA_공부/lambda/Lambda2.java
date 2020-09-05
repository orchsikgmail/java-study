package me.blacksheepbell;

// 두 정수의 합을 구하는 함수
public class Lambda2 {
	// 준비물 - 대상 함수
	public static int sum(IntegerOperation op) {
		int a = 10;
		int b = 20;
		int sum = op.operation(a, b);
		return sum;
	}
	
	// 실행
	public static void main(String[] args) {
		// NoLambda
		int sum = sum(new IntegerOperation() {
			@Override
			public int operation(int a, int b) {
				return a+b;
			}
		});
		System.out.println(sum);
		
		// YesLambda
		int sum2 = sum((a, b)->{
			return a+b;
		});
		System.out.println(sum2);
	}
}

// 준비물 - 인터페이스
interface IntegerOperation{
	int operation(int a, int b);
}