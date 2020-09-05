package interviews.java.algorithm.ex;

public class Gugudan {

	public static void main(String[] args) {
		for(int i=2; i<=9; i++) {
			System.out.printf("%d´Ü%n",i);
			for(int y=1; y<=9; y++) {
				System.out.printf("%d * %d = %d %n", i,y,i*y);
			}
			System.out.println();

		}



	}




}