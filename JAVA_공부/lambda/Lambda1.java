package me.blacksheepbell;

/* ���� ������
 * ���� : �ĺ��� ���� ���డ���� �Լ�
 * �͸��Լ��� ����, �ڵ� ���ٿ� �Լ��� �Ἥ ȣ���ϴ� ���.
 */

/* ���ٽ��� ����
 * 1. �ڵ尣��
 * 2. ���� -> �������ǵ� ��Ȯ -> ������ ����
 * 3. �ð�����
 * 4. �������α׷��ֿ� ����
 */

/* ���ٽ��� ����
 * 1. �͸��Լ��� ����ϱ⿡ ���� �Ұ�
 * 2. ����� �����
 * 3. ������ ���� �Լ��� ������ �� ������ �ڵ� ��������
 * 4. ��ͷ� ����⿡ ������
 */
public class Lambda1 {

	// ���� �غ� - �������̽�
	@FunctionalInterface
	interface Say{
		int something(int a, int b);
	}

	// ���� �غ� - Ŭ����
	class Person {
		public void hi(Say hoho) {
			int number = hoho.something(3, 4);
			System.out.println("Number is " + number);
		}
	}
	Person rin = new Person();

	// ���ٽ��� ������� ���� �Լ� ȣ��
	public void noLambda() {
		rin.hi(new Say() {
			@Override
			public int something(int a, int b) {
				System.out.printf("parameter number is %d, %d %n", a, b);
				return 7;
			}
		});
	}

	// ���ٽ��� ����� �Լ� ȣ��
	public void yesLambda() {
		rin.hi((a,b) -> {
			System.out.printf("parameter number is %d, %d %n", a, b);
			return 7;
		});
	}

	// ����
	public static void main(String[] args) {
		Lambda1 lambda = new Lambda1();
		lambda.noLambda();
		lambda.yesLambda();
	}

	
}
