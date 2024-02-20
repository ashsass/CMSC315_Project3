/*
 * Name: Ashlyn Sassaman
 * Due date: 2/20/24
 * Description: Parse the original user input and catch any errors including missing parentheses, and characters
 * that aren't digits, paranetheses, or astericks. Throw the error and allow user to re-enter a binary tree string. 
 */

public class InvalidTreeException extends Exception {
	public InvalidTreeException(String message) {
		super(message);
	}
}
