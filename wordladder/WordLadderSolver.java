/**
 * EE422C Assignment 4 - Word Ladder
 *
 * @author Cooper Raterink, Brandon Arrindell
 * UT EIDs: cdr2678, bja733
 * Lab Section: Friday 2-3:30pm with Mehtaab
 */
package wordladder;

import wordladder.errors.NoSuchLadderException;

import java.util.*;

import static wordladder.WordMap.letterDelta;

/**
 * Class for solving word ladders between two specified words.
 * Implements A4Interface for grading efficiency purposes.
 * @author Cooper, Brandon
 *
 */
public class WordLadderSolver implements A4Interface {

	/**
	 * Contains a resulting word ladder represented as a list of sequential words.
	 */
	private final ArrayList<String> result = new ArrayList<>();
	int tried = 0;

	/**
	 * Word map graph in for pathfinding algorithms by this class to solve word ladders.
	 */
	private final WordMap wordMap;


	public WordLadderSolver(WordMap map) {
		wordMap = map;
	}

	// do not change signature of the method implemented from the interface

	/**
	 * Method calling MakeLadder used in grading
	 *
	 * @param startWord Starting word in the ladder
	 * @param endWord   Ending word in the ladder
	 * @return Index of changed letter
	 */
	@Override
	public List<String> computeLadder(String startWord, String endWord) throws NoSuchLadderException {
		Boolean isLadder = false;
		result.clear();
		tried = 0;
		if(wordMap.containsKey(startWord) && wordMap.containsKey(endWord)) {
			isLadder = makeLadder(startWord, endWord, -1);
		}
		if (isLadder) return result;
		else throw new NoSuchLadderException("No ladder found between " + startWord + " and " + endWord);

	}


	@Override
	/**
	 * Validates a given word ladder (makes sure it is correct for the given start and end words
	 * @param startWord Starting word of the correct word ladder
	 * @param endWord Ending word of the correct word ladder
	 * @param wordLadder Word Ladder to check if correct for the given start and end words
	 * @return true if word ladder is valid, false otherwise
	 */
	public boolean validateResult(String startWord, String endWord, List<String> wordLadder) {
		//1) Make sure start and end words are correct
		if(!wordLadder.get(0).equals(startWord)) return false;
		if(!wordLadder.get(wordLadder.size()-1).equals(endWord)) return false;

		//2) Make sure all words in wordLadder are valid (i.e., they should be keys in the wordmap)
		for(String word : wordLadder) {
			if(wordMap.get(word) == null) return false;
		}

		//3) Make sure all sequential words differ by exactly one letter
		for(int i = 1; i < wordLadder.size(); i++) {
			if(WordMap.letterDelta(wordLadder.get(i), wordLadder.get(i-1))!= 1) return false;
		}

		//4)Make sure there's no duplicate words
		if(wordLadder.size() != (new HashSet<String>(wordLadder)).size()) return false;

		//If all these conditions are met, it's a valid word ladder.
		return true;
	}


	/**
	 * Method to create a word ladder between two words
	 *
	 * @param fromWord Starting word in the ladder
	 * @param toWord Ending word in the ladder
	 * @param index Index of letter previously changed
	 * @return boolean of whether a ladder exists
	 */
	private boolean makeLadder(String fromWord, String toWord, int index) {
		// Add the current word to the ladder
		result.add(fromWord);

		//Initialize variables
		ArrayList<String> candidateList;
		ArrayList<Integer> candIndicies;

		//Find how many letters differ between the current word and word we want
		Integer differ = letterDelta(toWord, fromWord);
		//If only one, we have found our ladder, return add final word and return true
		if (differ == 1) {
			result.add(toWord);
			return true;
		}
		//If not, get a list of candidates for the current word with their indicies
		else {
			WordsAndIndex fromKey = wordMap.get(fromWord);
			candidateList = fromKey.getCandidateWords();
			candIndicies = fromKey.getChangedIndices();

		}

		//Sort candidates on how different they are to the final word.
		HashMap<String, Integer> candidateLookup = new HashMap<>();
		for (int i = 0; i < candidateList.size(); i++) {
			Integer diff = letterDelta(candidateList.get(i), toWord);
			candidateLookup.put(candidateList.get(i), candIndicies.get(i));
			candidateList.set(i, diff.toString() + candidateList.get(i));
		}
		Collections.sort(candidateList);

		for (int i = 0; i < candidateList.size(); i++) {
			candidateList.set(i, candidateList.get(i).substring(1));
		}

		/*For every candidate, check that it
		 * 1. Does not already exist in the ladder
		 * 2. Does not have the same letter changed as the previous word
		 * If those conditions are satisfied, call MakeLadder with the new word.
		 */
		for (int i = 0; i < candidateList.size(); i++) {
			String candidate = candidateList.get(i);
			int newInt = candidateLookup.get(candidate);
			if (!result.contains(candidate) && index != newInt) {
				if (makeLadder(candidate, toWord, newInt)) {
					return true;
				} else if (tried == wordMap.size()) {
					return false;
				}
			}
		}
		//If no candidates match the conditions, remove the word from the ladder and return false.
		result.remove(fromWord);
		tried++;
		return false;
	}

}
