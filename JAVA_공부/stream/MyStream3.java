package me.blacksheepbell.stream;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MyStream3 {

	public static void main(String[] args) {
		MyStream3 ms = new MyStream3();
		ms.solution();
		System.out.println();
		ms.solution2();
		System.out.println();
		ms.solution3();
		System.out.println();
	}

	public void solution() {
		Stream<String> stream = Stream.of("수정", "현웅", "미정");
		Optional<String> result = stream.reduce((s1,s2) -> s1+"♥"+s2);
		result.ifPresent(System.out::println); // 수정♥현웅♥미정
		
		Stream<String> stream2 = Stream.of("수정", "현웅", "미정");
		String result2 = stream2.reduce("아잉", (s1,s2) -> s1+"♥"+s2);
		System.out.println(result2);  // 아잉♥수정♥현웅♥미정
	}
	
	
	// Stream -> List
	public void solution2() {
		Stream<String> stream = Stream.of("일본 ", "미쳤어? ", "응");
		
		List<String> list = stream.collect(Collectors.toList());
		
		Iterator<String> iterator = list.iterator();
		while (iterator.hasNext()) {
			System.out.print(iterator.next());
		}
		// 일본 미쳤어?응
	}
	
	// Stream -> 글자수별로 나누어 저장
	public void solution3() {
		Stream<String> stream = Stream.of("일", "이이", "삼삼삼", "사사사사");
		
		Map<Boolean, List<String>> partition = stream.collect(Collectors.partitioningBy( 
				s -> s.length()%2!=0
				));
		
		List<String> oddLength = partition.get(true);
		for(String s : oddLength) {
			System.out.println(s);  // 일 삼삼삼
		}
		List<String> evenLength = partition.get(false);
		for(String s : evenLength) {
			System.out.println(s);  // 이이 사사사사
		}
	}
	
	
	
	
	
	
}
