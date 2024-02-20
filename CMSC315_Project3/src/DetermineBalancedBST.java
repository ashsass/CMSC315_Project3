/*
 * Name: Ashlyn Sassaman
 * Due date: 2/20/24
 * Description: Ask the user for a binary tree input. Display the tree and determine if it is a binary search tree or not. If it is a binary search tree and is balanced, then ask the user if they want to input more trees. If it is a binary search tree but not balanced, redisplay the balanced tree and report the height of both trees. If it is not a binary search tree, still redisplay the values but as a binary search tree and report the heights.
 */

import java.util.*;

public class DetermineBalancedBST {

	public static String moreTrees = "Y";
	

	public static void main(String[] args) {
		String bstInput;
		Scanner reader = null;
		int originalHeight = 0;
		
		while(moreTrees.equals("Y")) {
			try {
				//Take in user input
				System.out.print("Enter a binary tree: ");
				reader = new Scanner(System.in);
				bstInput = reader.nextLine();
				
				//Call tree constructor to construct a tree
				BinaryTree tree = new BinaryTree(bstInput);
				
				//Determine if the tree is a binary search tree and/or balanced
				String message = tree.isBST(tree.getRoot()) && 
						tree.isBalanced(tree.getRoot()) ? "It is a balanced binary search tree." : 
							tree.isBST(tree.getRoot()) ? "It is a binary search tree but it is not balanced." :
								"It is not binary search tree.";
				System.out.println(message);
				
				//If the tree is not a bst or it is not balanced
				if(!tree.isBST(tree.getRoot()) || !tree.isBalanced(tree.getRoot())) {
					originalHeight = tree.height();
					BinaryTree newTree = new BinaryTree(tree.toArray());
					System.out.printf("Original tree has height %d\nBalanced tree has height %d\n", originalHeight, newTree.height());
				}
				
				//Ask if user wants more trees:
				System.out.print("More trees? Y or N: ");
		        moreTrees = reader.nextLine();
				while(!moreTrees.equals("Y") && !moreTrees.equals("N")) {
					System.out.println("Invalid input, try again.");
					System.out.print("More trees? Y or N: ");
					moreTrees = reader.nextLine();
				}
			} 
			catch (InvalidTreeException ex) {
				System.out.println(ex);
			}
		}
		reader.close();
		System.out.println("Goodbye.");
	}

}
