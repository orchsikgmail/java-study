package me.blacksheepbell.stream;

import java.security.Principal;
import java.util.Arrays;
import java.util.stream.Stream;

public class MyStream2 {

	public static void main(String[] args) {
		MyStream2 ms = new MyStream2();
		ms.solution();
		System.out.println();
		ms.solution2();
		System.out.println();
		ms.solution3();
		System.out.println();
		ms.solution4();
		System.out.println();
	}

	public void solution() {
		Stream<Integer> stream = Stream.of(1,1,2,2,3,3);
		stream.distinct().forEach(System.out::println);  // 1 2 3

		Stream<Integer> stream2 = Stream.of(1,2,3,4,5,6);
		stream2.filter(e-> e%2!=0 ).forEach(System.out::println);  // 1 3 5
	}
	
	
	public void solution2() {
		Stream<String> stream = Stream.of("일", "이이", "삼삼삼");
		stream.map(e -> e.length()).forEach(System.out::println);  // 1 2 3
		
		String[] arr = {"남자: 지용운, 최현웅, 이경호", "여자: 김수정, 송미정"};
		Stream<String> stream2 = Arrays.stream(arr);
		stream2.flatMap(s -> Stream.of(s.split(":"))).forEach(System.out::println);
		// 남자
		//  지용운, 최현웅, 이경호
		// 여자
		//  김수정, 송미정
	}
	
	
	public void solution3() {
		Stream<Integer> stream = Stream.of(1,2,3,4,5,6);
		stream.skip(3).forEach(System.out::println);  // 4 5 6
		
		Stream<Integer> stream2 = Stream.of(1,2,3,4,5,6);
		stream2.limit(2).forEach(System.out::println);  // 1 2
		
		// stream.limit(1).forEach(System.out::println);  
		// stream 재사용 불가 : stream has already been operated upon or closed
		Stream<Integer> stream3 = Stream.of(1,2,3,4,5,6);
		stream3.skip(3).limit(1).forEach(System.out::println);  // 4
	}
	
	
	public void solution4() {
		Stream<String> stream = Stream.of("송대관", "송미정", "송골매");
		stream.sorted().forEach(System.out::println); // 송골매 송대관 송미정
		
		Stream<Integer> stream2 = Stream.of(3,2,1);
		stream2.sorted().forEach(System.out::println);  // 1 2 3
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
