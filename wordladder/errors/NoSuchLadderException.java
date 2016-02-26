/**
 * EE422C Assignment 4 - Word Ladder
 * @author Cooper Raterink, Brandon Arrindell
 * UT EIDs: cdr2678, bja733
 * Lab Section: Friday 2-3:30pm with Mehtaab
 */
package wordladder.errors;

/**
 * Exception thrown when a ladder can't be formed between two words
 * @author Cooper, Brandon
 */
public class NoSuchLadderException extends Exception
{
	/**
	 * Serial ID for this exception class
	 */
    private static final long serialVersionUID = 1L;
    
    /**
     * Initializes a NoSuchLadderException, specifying a message associated with it
     * @param message Message describing the reason the thrown exception
     */
    public NoSuchLadderException(String message)
    {
        super(message);
    }

    /**
     * Initializes a NoSuchLadderException, specifying a message associated with it and a Throwable cause
     * @param message Message describing the thrown exception
     * @param throwable The cause of the thrown exception
     */
    public NoSuchLadderException(String message, Throwable throwable)
    {
        super(message, throwable);
    }
}
