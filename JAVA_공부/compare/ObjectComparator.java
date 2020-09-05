package interviews.java.compare;

import java.util.Arrays;
import java.util.Comparator;

// 객체에 Comparator인터페이스를 구현하여 정렬
// 객체는 Array, Collection의 정렬 메소드 사용 위해 Comparable,Comparator 구현해야한다. 
public class ObjectComparator{

	public void sortStudent(int num) {
		ObjectComparator[] studentArr = makeStudent(num);
		
		// Comparator객체를 익명의 객체로 생성해서 사용
		// 물론 Comparator를 구현한 클래스를 만들어서 해당 객체를 넣어줘도 가능하다.
		Arrays.sort(studentArr, new Comparator<ObjectComparator>() {
			@Override
			public int compare(ObjectComparator o1, ObjectComparator o2) {
				if(o1.getScore() > o2.getScore()) {
					return 1;
				} else if(o1.getScore() < o2.getScore()) {
					return -1;
				} else {
					return 0;
				}
			}
		});
		
		for(ObjectComparator s : studentArr) {
			System.out.printf("%d살 %s학생 %d점!!! \n", s.getAge(), s.getName(), s.getScore());
		}
	}
	
	
	public static void main(String[] args) {
		ObjectComparator s = new ObjectComparator();
		s.sortStudent(5);
	}
	
	
	
	private String name;
	private int age;
	private int score;
	
	public String getName() {
		return name;
	}
	public int getAge() {
		return age;
	}
	public int getScore() {
		return score;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public void setScore(int score) {
		this.score = score;
	}
	
	public ObjectComparator[] makeStudent(int num) {
		ObjectComparator[] studentArr = new ObjectComparator[num];

		for(int i=0; i<num; i++) {
			ObjectComparator s = new ObjectComparator();
			s.setName("user"+(i+1));
			s.setAge((int)(Math.random()*30+1));
			s.setScore((int)(Math.random()*100+1));

			studentArr[i]=s;
		}
		return studentArr;
	}

}



