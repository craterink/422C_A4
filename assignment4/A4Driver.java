package assignment4;

import wordladder.A4Interface;
import wordladder.WordLadderSolver;
import wordladder.WordMap;
import wordladder.errors.InvalidInputException;
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
			"C:\\Users\\Cooper\\workspace\\Ass4_WordLadder\\src\\A4-words.txt";

	/**
	 * Regex matching a five letter word in lowercase (as specified in this assignment)
	 */
	private static final String FIVE_LETTER_WORD_REGEX = "[a-z]{5}";
	
	/**
	 * Regex matching a valid input line for this assignment
	 */
	private static final String VALID_INPUT_LINE_REGEX = 
			String.format(" *%s +%s *", FIVE_LETTER_WORD_REGEX, FIVE_LETTER_WORD_REGEX);
	
	/**
	 * Index in args of the input file
	 */
	private static final int INPUT_FILE_ARGS_INDEX = 0;
	
	/**
	 * Index in the input (separated by the above delimiter) of the start word of the word ladder
	 */
	private static final int START_WORD_INPUT_INDEX = 0;
	
	/**
	 * Index in the input (separated by the above delimiter) of the end word of the word ladder
	 */
	private static final int END_WORD_INPUT_INDEX = 1;
	
	/**
	 * Main method satisfies requirements of EE422C assignment 4.
	 * @param args First argument should specify the input file that
	 * contains the words to make word ladders out of
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
			
			//Iterate through input file line by line and make a word ladder from the words specified by each valid input line
			FileReader freader = new FileReader(args[INPUT_FILE_ARGS_INDEX]);
			BufferedReader reader = new BufferedReader(freader);
			for (String line = reader.readLine(); line != null; line = reader.readLine()) {
				try {
					String[] words = parseInput(line, fiveLetterWordList);
					
					List<String> result = wordLadderSolver.computeLadder(words[START_WORD_INPUT_INDEX], words[END_WORD_INPUT_INDEX]);
					printLadder(result);

					boolean correct = wordLadderSolver.validateResult(words[START_WORD_INPUT_INDEX], words[END_WORD_INPUT_INDEX], result);
					int i = 4;
					i++;
				} catch (InvalidInputException iie) {
					System.out.println(iie.toString());
				} catch (NoSuchLadderException nsle) {
					System.out.println(nsle.toString());
				}
			}
			
		}
		catch (IOException e) { //File Error
			System.err.println("File Error: \nA4-words.txt file invalid or missing.\n"
					+ "OR\nInput file not specified, missing, or invalid.\n");
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
			Matcher m = Pattern.compile(FIVE_LETTER_WORD_REGEX).matcher(line);
			if(m.find() && line.indexOf(m.group(0)) == 0) {
				//add the valid word to our list of words
				flWords.add(m.group(0));
			}
		}
		return flWords;
	}					

	/**
	 * First checks the line for validity, throwing an exception if it's invalid,
	 * then if it's valid returns a string array containing start word and end word
	 * @param inputLine Line to parse for validity and for input start and ending words.
	 * @param validWords List of words considered valid in this assignment's scope.
	 * @return String array containing the input words if line is valid.
	 * @throws InvalidInputException If line is invalid
	 */
	private static String[] parseInput(String inputLine, ArrayList<String> validWords) throws InvalidInputException {
		boolean validFormat = inputLine.matches(VALID_INPUT_LINE_REGEX);
		String[] inputWords = inputLine.replaceFirst(" *", "").split(" +");
		boolean wordsInDictionary = validWords.contains(inputWords[START_WORD_INPUT_INDEX]) 
										 && validWords.contains(inputWords[END_WORD_INPUT_INDEX]); 
		if(validFormat && wordsInDictionary)
			return inputWords;
		else
			throw new InvalidInputException("Error - Input line is invalid: " + inputLine);
	}
	
	/**
	 * Prints the specified word ladder to the standard output for user inspection.
	 * @param ladder Word Ladder to print.
	 */
	private static void printLadder(List<String> ladder) {
		for (String word : ladder) {
			System.out.println(word);																		
		}
		System.out.println("**********");
	}
}
