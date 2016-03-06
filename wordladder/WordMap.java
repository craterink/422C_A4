/**
 * EE422C Assignment 4 - Word Ladder
 *
 * @author Cooper Raterink, Brandon Arrindell
 * UT EIDs: cdr2678, bja733
 * Lab Section: Friday 2-3:30pm with Mehtaab
 */
package wordladder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Class representing a graph/map of connected words, of equal length and differing by exactly one letter.
 *
 * @author Cooper, Brandon
 */
public class WordMap {

    /**
     * Graph/map connecting words differing by exactly one letter
     */
    private final HashMap<String, WordsAndIndex> map;

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
    private static HashMap<String, WordsAndIndex> makeWordMap(ArrayList<String> words) {
        //initialize hashmap to represent a graph of connected words
        HashMap<String, WordsAndIndex> map = new HashMap<String, WordsAndIndex>();
        //go through every word and compare to every other words, looking for valid connections
        for (String word : words) {
            ArrayList<String> connectedWords = new ArrayList<String>();
            ArrayList<Integer> indicies = new ArrayList<Integer>();
            for (String cmpWord : words) {
                //if it is different by exactly one letter and is the same length
                if (letterDelta(word, cmpWord) == 1 && word.length() == cmpWord.length()) {
                    connectedWords.add(cmpWord);
                    indicies.add(whichIndex(word, cmpWord));

                }
            }
            //add the word to the graph as a new node with edges directed towards connected words
            map.put(word, new WordsAndIndex(connectedWords, indicies));
        }
        return map;
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

    /**
     * Method telling the index of which letter is changed.
     *
     * @param word1 First word for comparison
     * @param word2 Second word for comparison
     * @return Index of changed letter
     */
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

    public WordsAndIndex get(Object key) {
        return map.get(key);
    }

    /**
     * Method for telling if a word is in map.
     *
     * @param key word in map
     * @return true if word exists, false if not
     */
    public boolean containsKey(Object key) {
        return map.containsKey(key);
    }

    /**
     * Method for telling the size of the map.
     *
     * @return the size of the map
     */
    public int size() {
        return this.map.size();
    }
    
    /**
     * 
     * @return List of valid five letter words
     */
    public ArrayList<String> getFiveLetterWordList() {
   	 return new ArrayList<String>(map.keySet());
    }
}