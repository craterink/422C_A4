package assignment4;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import wordladder.*;
import wordladder.errors.NoSuchLadderException;

public class A4Driver
{
	/**
	 * File containing the list of all five letter words
	 */
	public static final String FIVE_LETTER_WORDS_FILE = 
			"C:\\Users\\Cooper\\workspace\\Ass4_WordLadder\\src\\A4-words.txt";

	/**
	 * Main method satisfies requirements of EE422C assignment 4.
	 * @param args doesn't take any arguments
	 */
	public static void main(String[] args)
	{
		try 
		{
			ArrayList<String> testWordList = getFiveLetterWords();
			HashMap<String, ArrayList<String>> testMap = WordMap.makeWordMap(testWordList);
			// Create a word ladder solver object
			A4Interface wordLadderSolver = new WordLadderSolver();
			
			List<String> result = wordLadderSolver.computeLadder("money", "honey");
			boolean correct = wordLadderSolver.validateResult("money", "honey", result);
		} 
		catch (NoSuchLadderException e) 
		{
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static ArrayList<String> getFiveLetterWords() throws IOException {
		//read each line from file - parse for valid words
		FileReader freader = new FileReader(FIVE_LETTER_WORDS_FILE);
		BufferedReader reader = new BufferedReader(freader);
		ArrayList<String> flWords = new ArrayList<String>();
		for (String line = reader.readLine(); line != null; line = reader.readLine()) 
		{
			Matcher m = Pattern.compile("[a-z]{5}").matcher(line);
			if(m.find() && line.indexOf(m.group(0)) == 0) {
				flWords.add(m.group(0));
			}
		}
		return flWords;

	}
}
