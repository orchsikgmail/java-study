package interviews.java.algorithm.ex;

public class TriangleRight {

	public static void main(String[] args) {
		
		int total_line=5;
		int line=0;
		int blank=0;
		int star =0;
		
		for(line =1; line<=5; line++) {
			
			for(blank=1; blank<=total_line-line; blank++) {
				System.out.print(" ");
			}
			
			
			for(star =1; star<=line; star++) {
				System.out.print("*");
			}
			
			System.out.println();
		}
		
		
		
	}
}
