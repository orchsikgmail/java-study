package me.blacksheepbell.demo;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// 람다식을 추가하면서 같이 추가된 Stream API를 통해 람다식 이해하기
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

		/* 람다식 이용
		   .forEach((elem)->System.out.println(elem));
		 */
		
		/*
		 * 단순히 list의 요소를 println 메소드에 전달해주는 역할만 하고 있다.
		 * 그냥 System클래스가 가진 println메소드를 forEach에게 전달 하고싶어.
		 * 람다식 구현 가능한 경우, 메소드 참조용 특수문법이 존재.
		 * 메소드 참조(Method Reference)라고 표현.
		 */
			.forEach(System.out::print);	
		
/* 메소드 참조(Method Reference) 종류
# 클래스::인스턴스메소드(public)
첫번째 파라미터가 메소드의 주인이 되고, 나머지 파라미터는 해당 메소드로 전달 됩니다.
ex1-1) (x, y) -> x.compareToIgnoreCase(y)
ex1-2) String::compareToIgnoreCase

# 클래스::정적메소드(static)
두 번째 형태에서는 모든 파라미터가 정적 메소드로 전달됩니다. 
ex2-1) x -> Object.isNull(x)
ex2-2) Object::isNull

# 객체::인스턴스메소드(new)
세 번째 형태에서는 주어진 객체에서 메소드가 호출되며 파라미터는 인스턴스 메소드로 전달 됩니다.
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
