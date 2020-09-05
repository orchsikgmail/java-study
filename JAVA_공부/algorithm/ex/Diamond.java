package interviews.java.algorithm.ex;

public class Diamond {

	public static void main(String[] args) {
		
		int top_line = 2;
		int total_line= (2*top_line)+1;
		int total_block = total_line;
		int line;
		
		for(line=1; line<=total_line; line++) {
			if(line<=top_line+1) {
				int star = 2*line-1;
				int front_blank = (total_block-star)/2;
				int back_blank = front_blank;
				
				for(int cnt=1; cnt<=front_blank; cnt++) {
					System.out.print(" ");
				}
				for(int cnt=1; cnt<=star; cnt++) {
					System.out.print("*");
				}
				for(int cnt=1; cnt<=back_blank; cnt++) {
					System.out.print(" ");
				}
			} else {
				int star = 2*(total_line-line)+1;
				int front_blank = (total_block-star)/2;
				int back_blank = front_blank;
				
				for(int cnt=1; cnt<=front_blank; cnt++) {
					System.out.print(" ");
				}
				for(int cnt=1; cnt<=star; cnt++) {
					System.out.print("*");
				}
				for(int cnt=1; cnt<=back_blank; cnt++) {
					System.out.print(" ");
				}
			}
			System.out.println();
		}
		
		
		
	}
	
}
