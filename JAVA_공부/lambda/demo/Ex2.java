package me.blacksheepbell.demo;

public class Ex2 {
	
	// NoLambda
	static Func add2 = new Func() {
		@Override
		public int calc(int a, int b) {
			return a+b;
		}

	};
	
	// YesLambda
	static Func add = (a,b) -> a+b;
	static Func sub = (a,b) -> a-b;
	static Func getMax = (a,b) -> a>b? a:b;
	static Func getMin = (int a, int b) -> {
		return a>b? b:a;
	};

	// 구현
	public static void main(String[] args) {
		System.out.println(add.calc(1, 2));
		System.out.println(sub.calc(1, 2));
		System.out.println(getMax.calc(1, 2));
		System.out.println(getMin.calc(1, 2));
	}
	
	
}

/*
 * 람다식을 위한 인터페이스 : 하나의 추상메소드
 * @FunctionalInterface : 메소드 추가시 컴파일 오류 확인
 */
@FunctionalInterface
interface Func {
	public int calc(int a, int b);
}