/**
 * EE422C Assignment 4 - Word Ladder
 *
 * @author Cooper Raterink, Brandon Arrindell
 * UT EIDs: cdr2678, bja733
 * Lab Section: Friday 2-3:30pm with Mehtaab
 */
package wordladder;

import wordladder.errors.NoSuchLadderException;

import java.util.ArrayList;
import java.util.List;

import static wordladder.WordMap.letterDelta;

// do not change class name or interface it implements
public class WordLadderSolver implements A4Interface {
	private final ArrayList<String> result = new ArrayList<>();
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
		Boolean isLadder = MakeLadder(startWord, endWord, -1);
		if (isLadder) return result;
		else throw new NoSuchLadderException("No ladder found between " + startWord + " and " + endWord);
	}

	@Override
	public boolean validateResult(String startWord, String endWord, List<String> wordLadder) {
		throw new UnsupportedOperationException("Not implemented yet!");
	}


	/**
	 * Method to create a word ladder between two words
	 *
	 * @param fromWord Starting word in the ladder
	 * @param toWord Ending word in the ladder
	 * @param index Index of letter previously changed
	 * @return boolean of whether a ladder exists
	 */
	private boolean MakeLadder(String fromWord, String toWord, int index) {
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
			wordsAndIndex fromKey = wordMap.get(fromWord);
			candidateList = fromKey.getS();
			candIndicies = fromKey.getJ();

		}
		/*For every candidate, check that it
		 * 1. Does not already exist in the ladder
		 * 2. Does not have the same letter changed as the previous word
		 * If those conditions are satisfied, call MakeLadder with the new word.
		 */
		for (int i = 0; i < candidateList.size(); i++) {
			if(!result.contains(candidateList.get(i)) && index != candIndicies.get(i)) {
				String candidate = candidateList.get(i);
				int newInt = candIndicies.get(i);
				if (MakeLadder(candidate, toWord, newInt)) {
					return true;
				}
			}
		}
		//If no candidates match the conditions, remove the word from the ladder and return false.
		result.remove(fromWord);
		return false;
	}

}
