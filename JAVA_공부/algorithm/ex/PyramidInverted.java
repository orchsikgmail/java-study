package interviews.java.algorithm.ex;

public class PyramidInverted {

	public static void main(String args[]) {
		
		int total_line =5;
		int total = (2*total_line)-1;
		int line = 0;
		int star =0;
		int blank =0;

		for(line=1; line<=total_line; line++) {
			
			int star_cnt = 2*(total_line-line)+1;
			int fblank_cnt = (total-star_cnt)/2;
			int bblank_cnt = (total-star_cnt)/2;
			
			for(blank=1; blank<=fblank_cnt; blank++) {
				System.out.print(" ");
			}
			
			for(star=1; star<=star_cnt; star++) {
				System.out.print("*");
			}

			for(blank=1; blank<=bblank_cnt; blank++) {
				System.out.print(" ");
			}
			System.out.println();
		}

	}

}
