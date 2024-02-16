/*
 * Name: Ashlyn Sassaman
 * Due date: 2/20/24
 * Description: 
 */

import java.util.*;
import java.io.*;

public class DetermineBalancedBST {
	

	public static void main(String[] args) {
		//Take in user input
		//Scanner input = new Scanner(System.in);
		//System.out.print("Enter a binary tree: ");
		String bstInput;
		File file = new File("/Users/ashlynsassaman/git/CMSC315_Project3/CMSC315_Project3/test");
		ArrayList<Integer> values = new ArrayList<>();
		
		Scanner reader;
		try {
			reader = new Scanner(file);
			bstInput = reader.nextLine();
			
			//Call tree constructor to construct a tree
			BinaryTree tree = new BinaryTree(bstInput);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		//Display the tree
		
		//Determine if the tree is a bst or balanced, etc
			//If it is ask if user wants to input more binary search trees
			//If not call the arraylist method and call the constructor with the al values to recreate a balanced/proper tree
				//Report height of original tree and rebalanced tree
		
		//Ask user if want to recreate more trees

	}

}
