package assignment4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import wordladder.*;
import wordladder.errors.NoSuchLadderException;

public class A4Driver
{
	public static final String FIVE_LETTER_WORDS_FILE = "/Ass4_WordLadder/src/A4-words.txt";
	
	public static void main(String[] args)
   {
		ArrayList<String> testWordList = new ArrayList<String> ();
		testWordList.add("gaze");
		testWordList.add("graze");
		testWordList.add("craze");
		testWordList.add("taze");
		testWordList.add("gate");
		testWordList.add("gale");
		testWordList.add("rate");
		testWordList.add("pale");
		testWordList.add("omen");
		HashMap<String, ArrayList<String>> testMap = WordMap.makeWordMap(testWordList);
        // Create a word ladder solver object
        A4Interface wordLadderSolver = new WordLadderSolver();

        try 
        {
            List<String> result = wordLadderSolver.computeLadder("money", "honey");
            boolean correct = wordLadderSolver.validateResult("money", "honey", result);
        } 
        catch (NoSuchLadderException e) 
        {
            e.printStackTrace();
        }
   }
}
