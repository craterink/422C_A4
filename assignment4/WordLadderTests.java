package assignment4;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import wordladder.A4Interface;
import wordladder.WordLadderSolver;
import wordladder.WordMap;

/**
 * Class for testing JUnit test cases
 * @author Cooper, Brandon
 */
public class WordLadderTests {

	/**
	 * Used to test the computeLadder and validateResults in the test cases
	 */
	public A4Interface solver = null;
	
	/**
	 * Sets up the test cases.
	 * Initializes a WordLadderSolver class to use for the test cases.
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		if (solver == null) {
			//formulate list of valid five-letter words and a wordmap from that list
			ArrayList<String> fiveLetterWordList = A4Driver.getFiveLetterWords();
			WordMap wordMap = new WordMap(fiveLetterWordList);
			// Create a word ladder solver object
			solver = new WordLadderSolver(wordMap);
		}
	}

//---------- computeLadder tests ----------
	
	
	/**
	 * Shouldn't compute a ladder that isn't from a five letter word to a five letter word
	 */
	@Test
	public void fiveLettersTest() {
		
	}
	
	/**
	 * Shouldn't compute a ladder that isn't from a valid word to a valid word
	 */
	@Test
	public void notAWordTest() {
		
	}
	
	/**
	 * TODO: What should this do?
	 * How should program handle null params?
	 */
	@Test
	public void nullLadderTest() {
		
	}
	
	/**
	 * Should throw a NoSuchLadderException when computing an "impossible" ladder
	 */
	@Test
	public void noSuchLadderTest() {
		
	}
	
	/**
	 * Should be able to make a word ladder from "dears" to "fears"
	 */
	@Test
	public void easyLadderTest() {
		
	}
	
	/**
	 * Should be able to make a word ladder from "stone" to "money"
	 */
	@Test
	public void hardLadderTest() {
		
	}
	
	/**
	 * TODO: WHAT SHOULD THIS DO??
	 * Make a word ladder from "heart" to "heart"
	 */
	@Test
	public void trivialLadderTest() {
		
	}
	
//---------- validateResult tests ----------
	
	/**
	 * Should be able to validate a correct ladder
	 */
	@Test
	public void validateSimpleLadderTest() {
		ArrayList<String> testLadder = new ArrayList<String>();
		testLadder.add("dears");
		assertTrue(solver.validateResult("dears", "dears", testLadder));
		testLadder.add("fears");
		assertTrue(solver.validateResult("dears", "fears", testLadder));
		testLadder.add("gears");
		assertTrue(solver.validateResult("dears", "gears", testLadder));
	}
	
	/**
	 * Should not validate a ladder with incorrect start/end words
	 */
	@Test
	public void validateStartEndTest() {
		
	}
	
	/**
	 * Should not validate a ladder with any invalid words
	 */
	@Test
	public void validateInvalidWordsTest() {
		
	}
	
	/**
	 * Should not validate a ladder with sequential words not differing by exactly one letter 
	 */
	@Test
	public void validateChangesTest() {
		
	}
	
	/**
	 * Should not validate a ladder with duplicate words
	 */
	@Test
	public void validateDuplicateWordsTest() {
		
	}
}
