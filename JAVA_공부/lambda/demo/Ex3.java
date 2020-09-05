package me.blacksheepbell.demo;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// ���ٽ��� �߰��ϸ鼭 ���� �߰��� Stream API�� ���� ���ٽ� �����ϱ�
public class Ex3 {
	public static void main(String[] args) {
		Arrays.asList(1,2,3).stream()
		/* 
			.forEach(new Consumer<Integer>() {
				@Override
				public void accept(Integer elem) {
					System.out.println(t);
				}
			});
		 */

		/* ���ٽ� �̿�
		   .forEach((elem)->System.out.println(elem));
		 */
		
		/*
		 * �ܼ��� list�� ��Ҹ� println �޼ҵ忡 �������ִ� ���Ҹ� �ϰ� �ִ�.
		 * �׳� SystemŬ������ ���� println�޼ҵ带 forEach���� ���� �ϰ�;�.
		 * ���ٽ� ���� ������ ���, �޼ҵ� ������ Ư�������� ����.
		 * �޼ҵ� ����(Method Reference)��� ǥ��.
		 */
			.forEach(System.out::print);	
		
/* �޼ҵ� ����(Method Reference) ����
# Ŭ����::�ν��Ͻ��޼ҵ�(public)
ù��° �Ķ���Ͱ� �޼ҵ��� ������ �ǰ�, ������ �Ķ���ʹ� �ش� �޼ҵ�� ���� �˴ϴ�.
ex1-1) (x, y) -> x.compareToIgnoreCase(y)
ex1-2) String::compareToIgnoreCase

# Ŭ����::�����޼ҵ�(static)
�� ��° ���¿����� ��� �Ķ���Ͱ� ���� �޼ҵ�� ���޵˴ϴ�. 
ex2-1) x -> Object.isNull(x)
ex2-2) Object::isNull

# ��ü::�ν��Ͻ��޼ҵ�(new)
�� ��° ���¿����� �־��� ��ü���� �޼ҵ尡 ȣ��Ǹ� �Ķ���ʹ� �ν��Ͻ� �޼ҵ�� ���� �˴ϴ�.
ex3-1) x -> System.out.println(x)
ex3-2) System.out::println	
*/	
		
		
		System.out.println();
		Arrays.asList(1,2,3).stream()
			.map(elem -> elem*elem)
			.forEach(System.out::print);	// 1,4,9
		
		
		System.out.println();
		Arrays.asList(1,2,3).stream()
			.filter(i -> i>=2)
			.forEach(System.out::print);
			
		
		System.out.println();
		Arrays.asList(1,2,3).stream()
			.limit(1)
			.forEach(System.out::print);	// 1
		
		
		System.out.println();
		Arrays.asList(Arrays.asList(1,2,3), Arrays.asList(4,5,6))
			.stream()
			.flatMap(elem -> elem.stream())
			.forEach(System.out::print);	// 1,2,3,4,5,6
		
		
		System.out.println();
		List<Integer> collected = Stream.of(1,2,3).collect(Collectors.toList());
		collected.stream().forEach(System.out::print);
		
		
	}
}
