package me.blacksheepbell.demo;

public class Ex2_addGeneric {
	
	// NoLambda
	static Func2<Integer, Integer> add2 = new Func2<Integer, Integer>() {
		@Override
		public Integer calc(Integer a, Integer b) {
			return a+b;
		}

	};
	
	// YesLambda
	static Func2<Integer,Integer> add = (a, b) -> a+b;
	static Func2<Integer,Integer> sub = (a, b) -> a-b;
	static Func2<Integer,Integer> getMax = (a,b) -> a>b? a:b;
	static Func2<Integer,Integer> getMin = (Integer a, Integer b) -> {
		return a>b? b:a;
	};
	static Func2<Integer,Boolean> check = (a, b) -> a==b;

	// ����
	public static void main(String[] args) {
		System.out.println(add.calc(1, 2));
		System.out.println(sub.calc(1, 2));
		System.out.println(getMax.calc(1, 2));
		System.out.println(getMin.calc(1, 2));
		System.out.println(check.calc(1, 2));
	}
	
	
}

/*
 * ���ٽ��� ���� �������̽� : �ϳ��� �߻�޼ҵ�
 * @FunctionalInterface : �޼ҵ� �߰��� ������ ���� Ȯ��
 */
@FunctionalInterface
interface Func2<A,B> {
	public B calc(A a, A b);
}