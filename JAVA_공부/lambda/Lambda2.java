package me.blacksheepbell;

// �� ������ ���� ���ϴ� �Լ�
public class Lambda2 {
	// �غ� - ��� �Լ�
	public static int sum(IntegerOperation op) {
		int a = 10;
		int b = 20;
		int sum = op.operation(a, b);
		return sum;
	}
	
	// ����
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

// �غ� - �������̽�
interface IntegerOperation{
	int operation(int a, int b);
}