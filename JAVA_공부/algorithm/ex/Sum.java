package interviews.java.algorithm.ex;

public class Sum {

	public static void main(String[] args) {
		
		int startNum=1;
		int endNum=10;
		int sum=0;
		
		// ��
		for(int num=startNum; num<=endNum; num++) {
			sum += num;
		}
		System.out.println("����: "+sum);
	
		// Ȧ����
		sum=0;
		for(int num=startNum; num<=endNum; num++) {
			sum += num%2==0? 0:num; 
		}
		System.out.println("Ȧ����: "+sum);
		
		// ¦����
		sum=0;
		for(int num=startNum; num<=endNum; num++) {
			sum += num%2!=0? 0:num;
		}
		System.out.println("¦����: "+sum);
		
	}
	
	
	
	
}
