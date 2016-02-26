package wordladder;

import java.util.ArrayList;
import java.util.HashMap;

public class WordMap {
	
	
	
	public static HashMap<String, ArrayList<String>> makeWordMap(ArrayList<String> words) {
		HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
		for(String word : words) {
			ArrayList<String> connectedWords = new ArrayList<String>();
			for(String cmpWord : words) {
				//if it is different by exactly one letter and is the same length
				if(letterDelta(word, cmpWord) == 1 && word.length() == cmpWord.length()) { 
					connectedWords.add(cmpWord);
				}
			}
			map.put(word, connectedWords);
		}
		return map;
	}
	
	/**
	 * Method counting the number of letters changed between two words. This includes letters added.
	 * @param word1 First word for comparison
	 * @param word2 Second word for comparison
	 * @return Number of different letters. Note that this value includes
	 * number of extra letters in one of the words (i.e., letterDelta("c", "ca") returns 1)  
	 */
	public static int letterDelta(String word1, String word2) {
		//count different letters
		int letterDelta = 0;
		int i;
		for(i = 0; i < word1.length() && i < word2.length(); i++) {
			if(word1.charAt(i) != word2.charAt(i)) {
				letterDelta++;
			}
		}
		//count extra letters
		if(i == word1.length()) 
			letterDelta += word2.length() - word1.length();
		else 
			letterDelta += word1.length() - word2.length();
		return letterDelta;
	}
	
}
