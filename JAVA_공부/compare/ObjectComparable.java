package interviews.java.compare;

import java.util.Arrays;

// 객체에 Comparable인터페이스를 구현하여 정렬
//객체는 Array, Collection의 정렬 메소드 사용 위해 Comparable,Comparator 구현해야한다. 
public class ObjectComparable implements Comparable<ObjectComparable>{

	@Override
	public int compareTo(ObjectComparable o) {
		if(score > o.getScore()) {
			return 1;
		} else if(score < o.getScore()) {
			return -1;
		} else {
			return 0;
		}
	}
	
	public void sortStudent(int num) {
		ObjectComparable[] studentArr = makeStudent(num);
		Arrays.sort(studentArr);

		for(ObjectComparable s : studentArr) {
			System.out.printf("%d살 %s학생 %d점!!! \n", s.getAge(), s.getName(), s.getScore());
		}
	}
	
	public static void main(String[] args) {
		ObjectComparable s = new ObjectComparable();
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
	
	public ObjectComparable[] makeStudent(int num) {
		ObjectComparable[] studentArr = new ObjectComparable[num];

		for(int i=0; i<num; i++) {
			ObjectComparable s = new ObjectComparable();
			s.setName("user"+(i+1));
			s.setAge((int)(Math.random()*30+1));
			s.setScore((int)(Math.random()*100+1));

			studentArr[i]=s;
		}
		return studentArr;
	}

}



