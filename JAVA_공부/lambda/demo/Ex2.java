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

	// ����
	public static void main(String[] args) {
		System.out.println(add.calc(1, 2));
		System.out.println(sub.calc(1, 2));
		System.out.println(getMax.calc(1, 2));
		System.out.println(getMin.calc(1, 2));
	}
	
	
}

/*
 * ���ٽ��� ���� �������̽� : �ϳ��� �߻�޼ҵ�
 * @FunctionalInterface : �޼ҵ� �߰��� ������ ���� Ȯ��
 */
@FunctionalInterface
interface Func {
	public int calc(int a, int b);
}