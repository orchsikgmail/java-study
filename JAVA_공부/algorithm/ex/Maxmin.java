package interviews.java.algorithm.ex;

import java.util.Scanner;

public class Maxmin {

	public static void main(String[] args) {
		
		int num1;
		int num2;
		int max;
		int min;
		
		System.out.println("�� ���� ������ �Է��ϼ���.");
		
		Scanner sc = new Scanner(System.in);
		
		num1 = sc.nextInt();
		num2 = sc.nextInt();
		
		int a;
		int b;
		a = num1>num2? num1: num2;
		b = num1<num2? num1: num2;
		
		int mod = a%b;
		while(a%b != 0) {
			a=b;
			b=mod;
		}
		
		max = b;
		min = num1*num2/max;
		
		System.out.println("�ִ�����: "+max);
		System.out.println("�ּҰ����: "+min);
		
		sc.close();
	}
	
}
