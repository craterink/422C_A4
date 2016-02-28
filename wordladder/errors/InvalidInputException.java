package wordladder.errors;

/**
 * Exception thrown when the input is invalid for one of the following reasons:
 * 1) Input is in an invalid format (can't be parsed)
 * 2) Either or both of the input words aren't in the dictionary
 * @author Cooper, Brandon
 *
 */
public class InvalidInputException extends Exception {

	/**
	 * Exception class' default serial version ID
	 */
	private static final long serialVersionUID = 1L;

	/**
    * Initializes a InvalidInputException, specifying a message associated with it
    * @param message Message describing the reason the thrown exception
    */
   public InvalidInputException(String message)
   {
       super(message);
   }

   /**
    * Initializes a InvalidInputException, specifying a message associated with it and a Throwable cause
    * @param message Message describing the thrown exception
    * @param throwable The cause of the thrown exception
    */
   public InvalidInputException(String message, Throwable throwable)
   {
       super(message, throwable);
   }
	
}
