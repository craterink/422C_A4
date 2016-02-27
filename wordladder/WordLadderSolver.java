/**
 * EE422C Assignment 4 - Word Ladder
 *
 * @author Cooper Raterink, Brandon Arrindell
 * UT EIDs: cdr2678, bja733
 * Lab Section: Friday 2-3:30pm with Mehtaab
 */
package wordladder;

import wordladder.errors.NoSuchLadderException;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static wordladder.WordMap.letterDelta;
import static wordladder.WordMap.whichIndex;

// do not change class name or interface it implements
public class WordLadderSolver implements A4Interface {
	private final ArrayList<String> result = new ArrayList<>();
	private final WordMap wordMap;


	public WordLadderSolver(WordMap map) {
		wordMap = map;
	}

	// do not change signature of the method implemented from the interface
	@Override
	public List<String> computeLadder(String startWord, String endWord) throws NoSuchLadderException {
		MakeLadder(startWord, endWord, -1);
		return result;
	}

	@Override
	public boolean validateResult(String startWord, String endWord, List<String> wordLadder) {
		throw new UnsupportedOperationException("Not implemented yet!");
	}

	// add additional methods here
	private boolean MakeLadder(String fromWord, String toWord, int index) {
		result.add(fromWord);

		ArrayList<String> candidateList;
		ArrayList<Integer> candIndicies;
		Integer differ = letterDelta(toWord, fromWord);
		if (differ == 1) {
			result.add(toWord);
			return true;
		} else {
			wordsAndIndex fromKey = wordMap.get(fromWord);
			candidateList = fromKey.getS();
			candIndicies = fromKey.getJ();

		}
		for (int i = 0; i < candidateList.size(); i++) {
			if(!result.contains(candidateList.get(i)) && index != candIndicies.get(i)) {
				String candidate = candidateList.get(i);
				int newInt = candIndicies.get(i);
				if (MakeLadder(candidate, toWord, newInt)) {
					return true;
				}
			}
		}
		result.remove(fromWord);
		return false;
	}

}
