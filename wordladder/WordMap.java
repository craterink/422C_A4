package wordladder;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Class representing a graph/map of connected words, of equal length and differing by exactly one letter.
 * @author Cooper, Brandon
 *
 */
public class WordMap {
	
	/**
	 * Graph/map connecting words differing by exactly one letter
	 */
	private HashMap<String, ArrayList<String>> map;
	
	/**
	 * Initializes a new word map using the specified list of words.
	 * @param words List of words to form a word map out of
	 */
	public WordMap(ArrayList<String> words) {
		map = makeWordMap(words);
	}
	
	/**
	 * Makes a word map out of a list of words, connecting words differing
	 * by exactly one letter
	 * @param words List of words to form a graph/map
	 * @return A HashMap object representing the word map
	 */
	public static HashMap<String, ArrayList<String>> makeWordMap(ArrayList<String> words) {
		//initialize hashmap to represent a graph of connected words
		HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
		//go through every word and compare to every other words, looking for valid connections
		for(String word : words) {
			ArrayList<String> connectedWords = new ArrayList<String>();
			for(String cmpWord : words) {
				//if it is different by exactly one letter and is the same length
				if(letterDelta(word, cmpWord) == 1 && word.length() == cmpWord.length()) { 
					connectedWords.add(cmpWord);
				}
			}
			//add the word to the graph as a new node with edges directed towards connected words
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