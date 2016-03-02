package assignment4;

import org.junit.Before;
import org.junit.Test;
import wordladder.A4Interface;
import wordladder.WordLadderSolver;
import wordladder.WordMap;
import wordladder.errors.NoSuchLadderException;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
	@Test(expected = NoSuchLadderException.class)
	public void fiveLettersTest() throws NoSuchLadderException {
		solver.computeLadder("dog", "cat");
	}
	
	/**
	 * Shouldn't compute a ladder that isn't from a valid word to a valid word
	 */
	@Test(expected = NoSuchLadderException.class)
	public void notAWordTest() throws NoSuchLadderException {
		solver.computeLadder("sdfsd", "abcde");
	}
	
	/**
	 * TODO: What should this do?
	 * How should program handle null params?
	 */
	@Test(expected = NoSuchLadderException.class)
	public void nullLadderTest() throws NoSuchLadderException {
		solver.computeLadder(null, null);
	}
	
	/**
	 * Should throw a NoSuchLadderException when computing an "impossible" ladder
	 */
	@Test(expected = NoSuchLadderException.class)
	public void noSuchLadderTest() throws NoSuchLadderException {
		solver.computeLadder("hello", "world");
	}
	
	/**
	 * Should be able to make a word ladder from "dears" to "fears"
	 */
	@Test
	public void easyLadderTest() throws NoSuchLadderException {
		solver.computeLadder("dears", "fears");
	}
	
	/**
	 * Should be able to make a word ladder from "stone" to "money"
	 */
	@Test
	public void hardLadderTest() throws NoSuchLadderException {
		solver.computeLadder("stone", "money");
	}
	
	/**
	 * Make a word ladder from "heart" to "heart"
	 */
	@Test
	public void trivialLadderTest() throws NoSuchLadderException {
		solver.computeLadder("heart", "heart");

	}

	/**
	 * Test the word ladder 100 times with the first 200 words
	 */
	@Test
	public void stressTest() throws NoSuchLadderException {
		ArrayList<String> words = null;
		try {
			words = A4Driver.getFiveLetterWords();
		} catch (IOException e) {
			e.printStackTrace();
		}

		for (int i = 0; i < 200; i += 2) {
			try {
				solver.computeLadder(words.get(i), words.get(i + 1));
			} catch (NoSuchLadderException ignored) {
			}
		}
	}
//---------- validateResult tests ----------
	
	/**
	 * Should be able to validate a correct ladder
	 */
	@Test
	public void validateSimpleLadderTest() {
		ArrayList<String> testLadder = new ArrayList<String>();
		testLadder.add("dears");
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
		ArrayList<String> testLadder = new ArrayList<String>();
		testLadder.add("dears");
		testLadder.add("fears");
		assertFalse(solver.validateResult("tears", "fears", testLadder));
		testLadder.add("gears");
		assertFalse(solver.validateResult("tears", "gears", testLadder));
	}
	
	/**
	 * Should not validate a ladder with any invalid words
	 */
	@Test
	public void validateInvalidWordsTest() {
		ArrayList<String> testLadder = new ArrayList<String>();
		testLadder.add("heads");
		testLadder.add("abcde");//This makes it wrong
		testLadder.add("deals");
		testLadder.add("teals");
		testLadder.add("tells");
		testLadder.add("tills");
		testLadder.add("rills");
		testLadder.add("bills");
		testLadder.add("balls");
		testLadder.add("bawls");
		testLadder.add("bails");
		testLadder.add("tails");
		assertFalse(solver.validateResult("heads", "tails", testLadder));
	}
	
	/**
	 * Should not validate a ladder with sequential words not differing by exactly one letter 
	 */
	@Test
	public void validateChangesTest() {
		ArrayList<String> testLadder = new ArrayList<String>();
		testLadder.add("heads");
		testLadder.add("ready");//Two letters
		testLadder.add("heals");
		testLadder.add("deals");
		testLadder.add("teals");
		testLadder.add("tells");
		testLadder.add("tills");
		testLadder.add("rills");
		testLadder.add("bills");
		testLadder.add("balls");
		testLadder.add("bawls");
		testLadder.add("bails");
		testLadder.add("tails");
		assertFalse(solver.validateResult("heads", "tails", testLadder));
	}
	
	/**
	 * Should not validate a ladder with duplicate words
	 */
	@Test
	public void validateDuplicateWordsTest() {
		ArrayList<String> testLadder = new ArrayList<String>();
		testLadder.add("heads");
		testLadder.add("heads");//Duplicate word
		testLadder.add("heals");
		testLadder.add("deals");
		testLadder.add("teals");
		testLadder.add("tells");
		testLadder.add("tills");
		testLadder.add("rills");
		testLadder.add("bills");
		testLadder.add("balls");
		testLadder.add("bawls");
		testLadder.add("bails");
		testLadder.add("tails");
		assertFalse(solver.validateResult("heads", "tails", testLadder));
	}
}
