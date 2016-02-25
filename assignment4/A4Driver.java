package assignment4;

import java.util.List;

import wordladder.*;
import wordladder.errors.NoSuchLadderException;

public class A4Driver
{
    public static void main(String[] args)
    {
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
