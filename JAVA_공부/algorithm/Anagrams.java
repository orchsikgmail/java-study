package interviews.java.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// getAnagrams에 매개변수로 특정 단어를 입력하면, 
// 가지고 있는 lookup 리스트에서 
// 해당 단어의 알파벳을 재배열해서 만들 수 있는 단어들을 반환.
public class Anagrams {
	
	public static void main(String[] args) {
		List<String> words = new ArrayList<>();
		
		words.add("superman");
		words.add("mansuper");
		words.add("bladeSoul");
		
		Anagrams anagrams = new Anagrams(words);
		
		for(String s : anagrams.getAnagrams("superman")) {
			System.out.println(s);
		}
	}

	
	// 시그니쳐 키값과 일치하는 단어를 리스트밸류값에 추가해줌.
	// lookup: 시그니처 - 단어리스트
	final Map<String, List<String>> lookup = new HashMap<>();
	
	// 애너그램 생성시 단어리스트를 주고 lookup 맵 을 생성
	public Anagrams(final List<String> words) {
		for(final String word : words) {
			final String signature = alphabetize(word);
			if(lookup.containsKey(signature)) {
				lookup.get(signature).add(word);
			} else {
				final List<String> anagramList = new ArrayList<String>();
				anagramList.add(word);
				lookup.put(signature, anagramList);
			}
		}
	}
	
	
	// 주어진 단어의 알파벳을 알파벳순서로 정렬하여 반환
	private String alphabetize(final String word) {
		final byte[] bytes = word.getBytes();
		Arrays.sort(bytes);
		return new String(bytes);
	}
	
	
	public List<String> getAnagrams(final String word){
		final String signature = alphabetize(word);
		final List<String> anagrms = lookup.get(signature);
		return anagrms == null? new ArrayList<String>() : anagrms;
	}
	
	
}
