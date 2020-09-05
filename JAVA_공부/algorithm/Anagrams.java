package interviews.java.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// getAnagrams�� �Ű������� Ư�� �ܾ �Է��ϸ�, 
// ������ �ִ� lookup ����Ʈ���� 
// �ش� �ܾ��� ���ĺ��� ��迭�ؼ� ���� �� �ִ� �ܾ���� ��ȯ.
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

	
	// �ñ״��� Ű���� ��ġ�ϴ� �ܾ ����Ʈ������� �߰�����.
	// lookup: �ñ״�ó - �ܾ��Ʈ
	final Map<String, List<String>> lookup = new HashMap<>();
	
	// �ֳʱ׷� ������ �ܾ��Ʈ�� �ְ� lookup �� �� ����
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
	
	
	// �־��� �ܾ��� ���ĺ��� ���ĺ������� �����Ͽ� ��ȯ
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
