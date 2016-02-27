
package wordladder;

import java.util.*;

/**
 * Class representing a graph/map of connected words, of equal length and differing by exactly one letter.
 * @author Cooper, Brandon
 *
 */
public class WordMap {

	/**
	 * Graph/map connecting words differing by exactly one letter
	 */
	private final HashMap<String, wordsAndIndex> map;

	/**
	 * Initializes a new word map using the specified list of words.
	 *
	 * @param words List of words to form a word map out of
	 */
	public WordMap(ArrayList<String> words) {
		map = makeWordMap(words);
	}

	/**
	 * Makes a word map out of a list of words, connecting words differing
	 * by exactly one letter
	 *
	 * @param words List of words to form a graph/map
	 * @return A HashMap object representing the word map
	 */
	private static HashMap<String, wordsAndIndex> makeWordMap(ArrayList<String> words) {
		//initialize hashmap to represent a graph of connected words
		HashMap<String, wordsAndIndex> map = new HashMap<>();
		//go through every word and compare to every other words, looking for valid connections
		for (String word : words) {
			ArrayList<String> connectedWords = new ArrayList<>();
			ArrayList<Integer> indicies = new ArrayList<>();
			for (String cmpWord : words) {
				//if it is different by exactly one letter and is the same length
				if (letterDelta(word, cmpWord) == 1 && word.length() == cmpWord.length()) {
					connectedWords.add(cmpWord);
					indicies.add(whichIndex(word, cmpWord));

				}
			}
			//add the word to the graph as a new node with edges directed towards connected words
			map.put(word, new wordsAndIndex(connectedWords, indicies));
		}
		return map;
	}

	public Set<String> keySet() {
		return map.keySet();
	}

	public int size() {
		return map.size();
	}

	public boolean containsKey(Object key) {
		return map.containsKey(key);
	}

	public wordsAndIndex get(Object key) {
		return map.get(key);
	}

	public void clear() {
		map.clear();
	}

	public wordsAndIndex put(String key, wordsAndIndex value) {
		return map.put(key, value);
	}

	public wordsAndIndex remove(Object key) {
		return map.remove(key);
	}

	public boolean containsValue(Object value) {
		return map.containsValue(value);
	}

	public Set<Map.Entry<String, wordsAndIndex>> entrySet() {
		return map.entrySet();
	}

	public boolean isEmpty() {
		return map.isEmpty();
	}

	public void putAll(Map<? extends String, ? extends wordsAndIndex> m) {
		map.putAll(m);
	}

	public Collection<wordsAndIndex> values() {
		return map.values();
	}

	/**
	 * Method counting the number of letters changed between two words. This includes letters added.
	 *
	 * @param word1 First word for comparison
	 * @param word2 Second word for comparison
	 * @return Number of different letters. Note that this value includes
	 * number of extra letters in one of the words (resInd.e., letterDelta("c", "ca") returns 1)
	 */
	public static int letterDelta(String word1, String word2) {
		//count different letters
		int letterDelta = 0;
		int i;
		for (i = 0; i < word1.length() && i < word2.length(); i++) {
			if (word1.charAt(i) != word2.charAt(i)) {
				letterDelta++;
			}
		}
		//count extra letters
		if (i == word1.length())
			letterDelta += word2.length() - word1.length();
		else
			letterDelta += word1.length() - word2.length();

		return letterDelta;
	}

	public static int whichIndex(String word1, String word2) {
		int different = 0;
		int count = 0;
		for (int i = 0; i < word1.length() && i < word2.length(); i++) {
			if (word1.charAt(i) != word2.charAt(i)) {
				different = i;
				count++;
			}
		}
		if (count == 1) {
			return different;
		} else {
			return -1;
		}
	}
}