/*
 * Name: Ashlyn Sassaman
 * Due date: 2/20/24
 * Description: Parse the original user input and catch any errors. Throw the error and allow user to re enter
 * a binary tree string. 
 */

public class InvalidTreeException extends Exception {
//	private String message;
	//Must be able to identify:
	public InvalidTreeException() {
		
	}
	//incomplete tree
	
	//data not an integer
	
	//extra  characters at the end
	
	//Missing left or right parantheses
	public InvalidTreeException(Character message) {
		if(message == ')') {
			System.out.println("Error in user input. Missing a left paratheneses");
		}
		else if(message == '(') {
			System.out.println("Error in user input. Missing a right paratheneses");
		}
	}
}
