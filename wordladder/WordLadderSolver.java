/**
 * EE422C Assignment 4 - Word Ladder
 * @author Cooper Raterink, Brandon Arrindell
 * UT EIDs: cdr2678, bja733
 * Lab Section: Friday 2-3:30pm with Mehtaab
 */
package wordladder;

import java.util.List;

import wordladder.errors.NoSuchLadderException;

// do not change class name or interface it implements
public class WordLadderSolver implements A4Interface
{
	private WordMap wordMap;

	public WordLadderSolver(WordMap map) {
		wordMap = map;
	}
	// do not change signature of the method implemented from the interface
	@Override
	public List<String> computeLadder(String startWord, String endWord) throws NoSuchLadderException 
	{
		// implement this method
		throw new UnsupportedOperationException("Not implemented yet!");
	}

	@Override
	public boolean validateResult(String startWord, String endWord, List<String> wordLadder) 
	{
		throw new UnsupportedOperationException("Not implemented yet!");
	}

	// add additional methods here
}
