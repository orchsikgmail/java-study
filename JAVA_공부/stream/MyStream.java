package me.blacksheepbell.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

// 스트림 생성
public class MyStream {
	public static void main(String[] args) {
		MyStream ms = new MyStream();
		ms.solution();
		ms.solution2();
		ms.solution3();
		ms.solution4();
		System.out.println();
		ms.solution5();
		System.out.println();
		ms.solution6();
	}

	public void solution() {
		List<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		
		Stream<Integer> stream = list.stream();
		stream.forEach(System.out::print);
	}

	public void solution2() {
		String[] arr = {"호랑이", "형님", "재밌어!!!"};
		Stream<String> stream = Arrays.stream(arr);
		stream.forEach(x->System.out.print(x+" ")); // 호랑이 형님 재밌어!!!
	}
	
	
	public void solution3() {
		Stream<Double> stream = Stream.of(1.1, 1.2, 1.3);
		stream.forEach(System.out::println);  // 1.1  1.2  1.3
	}
	
	
	public void solution4() {
		IntStream intStream = IntStream.range(1, 3);
		intStream.forEach(System.out::print);  // 1 2
		
		IntStream intStream2 = IntStream.rangeClosed(1, 3);
		intStream2.forEach(System.out::print);  // 1 2 3
	}
	
	
	public void solution5() {
		IntStream intStream = new Random().ints(4);
		intStream.forEach(System.out::println);
		// -1176164693
		// 1243258950
		// 1813706404
		// -396676677
	}
	
	
	public void solution6() {
		Stream<Integer> stream = Stream.iterate(2,n -> n+2).limit(3);
		stream.forEach(System.out::println);  // 2 4 6
		
		Stream<Integer> stream2 = Stream.generate(() -> 123).limit(3);
		stream2.forEach(System.out::println);  // 123 123 123
	}
	
	
	public void solution7() {
		// Stream<String> stream = Files.lines(Path path);
	}
}
