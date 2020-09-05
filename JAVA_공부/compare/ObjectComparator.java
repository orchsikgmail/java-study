package interviews.java.compare;

import java.util.Arrays;
import java.util.Comparator;

// ��ü�� Comparator�������̽��� �����Ͽ� ����
// ��ü�� Array, Collection�� ���� �޼ҵ� ��� ���� Comparable,Comparator �����ؾ��Ѵ�. 
public class ObjectComparator{

	public void sortStudent(int num) {
		ObjectComparator[] studentArr = makeStudent(num);
		
		// Comparator��ü�� �͸��� ��ü�� �����ؼ� ���
		// ���� Comparator�� ������ Ŭ������ ���� �ش� ��ü�� �־��൵ �����ϴ�.
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
			System.out.printf("%d�� %s�л� %d��!!! \n", s.getAge(), s.getName(), s.getScore());
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



