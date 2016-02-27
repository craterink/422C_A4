package assignment4;

import wordladder.A4Interface;
import wordladder.WordLadderSolver;
import wordladder.WordMap;
import wordladder.errors.NoSuchLadderException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Driver for EE422C Assignment 4 - Word Ladder
 * @author Cooper Raterink, Brandon Arrindell
 * UT EIDs: cdr2678, bja733
 * Lab Section: Friday 2-3:30pm with Mehtaab
 */
public class A4Driver
{
	/**
	 * File containing the list of all five letter words
	 */
	private static final String FIVE_LETTER_WORDS_FILE =
			"/home/brandon/Lab1/422C_A4/A4-words.txt";

	/**
	 * Main method satisfies requirements of EE422C assignment 4.
	 * @param args doesn't take any arguments
	 */
	public static void main(String[] args)
	{
		try
		{
			//formulate list of valid five-letter words and a wordmap from that list
			ArrayList<String> fiveLetterWordList = getFiveLetterWords();
			WordMap wordMap = new WordMap(fiveLetterWordList);

			// Create a word ladder solver object
			A4Interface wordLadderSolver = new WordLadderSolver(wordMap);
			//TODO:Add input error handling and loop for more than one set of words.
			FileReader freader2 = new FileReader(args[0]);
			BufferedReader reader2 = new BufferedReader(freader2);
			String [] words = new String[2];

			for (String t = reader2.readLine(); t != null; t = reader2.readLine()) {
				words = t.split("\\s+");
			}
			List<String> result = wordLadderSolver.computeLadder(words[0], words[1]);
			printLadder(result);

			boolean correct = wordLadderSolver.validateResult("money", "honey", result);
		}
		catch (NoSuchLadderException e)
		{
			System.out.println("There is no such ladder between ...");
		} catch (IOException e) {
			System.out.println("A4-words.txt file invalid or missing.");
		}
	}

	/**
	 * Gets the list of valid English 5-letter words from the file given to us for A4
	 * @return List of valid English five letter words
	 * @throws IOException If the five-letter-word file is missing
	 */
	private static ArrayList<String> getFiveLetterWords() throws IOException {
		//read each line from file - parse for valid words
		FileReader freader = new FileReader(FIVE_LETTER_WORDS_FILE);
		BufferedReader reader = new BufferedReader(freader);
		//initialize a list to put valid 5-letter words in
		ArrayList<String> flWords = new ArrayList<>();
		//search for valid words in each line from the given file
		for (String line = reader.readLine(); line != null; line = reader.readLine())
		{
			//search in the line for the first five-letter word that is also at the beginning of the line
			Matcher m = Pattern.compile("[a-z]{5}").matcher(line);
			if(m.find() && line.indexOf(m.group(0)) == 0) {
				//add the valid word to our list of words
				flWords.add(m.group(0));
			}
		}
		return flWords;
	}

	private static void printLadder(List<String> ladder) {
		for (String aLadder : ladder) {
			System.out.println(aLadder);
		}
		System.out.println("**********");
	}
}
