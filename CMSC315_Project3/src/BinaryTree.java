/*
 * Name: Ashlyn Sassaman
 * Due date: 2/20/24
 * Description: Create a binary tree from user input string. Determine if the tree is a binary search tree. If it is not, take the values of the tree and add it to an ArrayList. Sort the ArrayList and redraw the input as a binary search tree. If the original user input was a binary search tree but it was not balanced, take the values as an ArrayList and rebalance the tree. 
 */

import java.util.*;

public class BinaryTree {
	private Node root;
	private int currentIndex;
	
	//no-arg constructor for best practice
	public BinaryTree() {
		
	}
	
	//Constructor that takes a string and constructs a tree
	public BinaryTree(String userInput) throws InvalidTreeException {
		currentIndex = 0;
		root = createTree(userInput);
		displayTree(root, 0);
	}
	
	//Construcor that takes in an AL of ints and makes a balanced tree 
	public BinaryTree(ArrayList<Integer> list) {
		root = createBalancedBST(list, 0, list.size() - 1);
		displayTree(root, 0);
	}
	
	private void validateString(String s) throws InvalidTreeException {
		Stack<Character> stack = new Stack<>();
		
		for(int i = 0; i < s.length(); i++) {
			//If a char is not a valid input char
			if(!Character.isDigit(s.charAt(i)) && 
					s.charAt(i) != '(' && s.charAt(i) != ')' && 
					s.charAt(i) != '*' && s.charAt(i) != ' ') {
				throw new InvalidTreeException("Error - unknown character found in string (" + s.charAt(i) + ")");
			}
			//Detect extra space at beginning of string or any any extra spaces that would throw off input reading
			else if((i == 0 && s.charAt(i) == ' ') ||
					(i > 1 && (s.charAt(i - 1) == ' ' && s.charAt(i) == ' ')))
				throw new InvalidTreeException("Error - extra spaces detected");
			
			else if(s.charAt(i) == '(') 
				stack.push(s.charAt(i));
			//Missing a right parantheses
			else if(s.charAt(i) == ')') {
				if(stack.isEmpty()) 
					throw new InvalidTreeException("Error - Missing a left parenthesis.");
			}
			
			//Missing a left parantheses
			else if(i == s.length() - 1 && !stack.isEmpty()) {
				if(stack.peek() == '(') 
					throw new InvalidTreeException("Error - Missing a right parenthesis.");
			}
		}
	}
	
	private Node createTree(String s) throws InvalidTreeException {
		//Use currentIndex to move through the string and find the end index for node/subtrees
		//Use start as the beginning point to the index
		validateString(s);
		currentIndex = 0;
		int start = currentIndex;
		
		//Remove outer () if the string is greater than one character
		if(s.length() > 1 && s.charAt(0) == '(') 
			s = s.substring(1, s.length() - 1);
		else 
			return null;
		
		
		//Want to find the amount of string that is actually a number to set the new node
		//So from start to currentIndex will have the value for a new node
		while(currentIndex < s.length() && Character.isDigit(s.charAt(currentIndex))) {
			currentIndex++;
		}
		
		//Create a node if the portion of the string was a number
		int value = Integer.parseInt(s.substring(start, currentIndex));
		Node node = new Node(value);
		
		
		//Use stack to find the index for the left and right subtree
		Stack<Character> stack = new Stack<>();
		currentIndex++; //skip the space
		start = currentIndex;
		for(int i = currentIndex; i < s.length(); i++) {
			if(s.charAt(i) == '(') 
				stack.push(s.charAt(i));
			else if(s.charAt(i) == ')') {
				if(!stack.isEmpty() && stack.peek() == '(') {
					stack.pop();
			
					if(stack.isEmpty()) {
						currentIndex = i;
						break;
					}
				}
			}
		}
		
		if(start != currentIndex && currentIndex > start) {
			String leftSubstring = s.substring(start, currentIndex + 1);
			String rightSubstring = s.substring(currentIndex + 2);
			node.left = createTree(leftSubstring);
			node.right = createTree(rightSubstring);
		}
			return node;
	}
	
	//Create new tree from ArrayList values
	private Node createBalancedBST(ArrayList<Integer> list, int start, int end) {
		if(list.isEmpty()) return null;
		else if(start > end) return null;
		else if(list.get(start) == list.get(end)) return new Node(list.get(start)); 
		
		int midpoint = (start + end) / 2;
		Node node = new Node(list.get(midpoint));
		node.left = createBalancedBST(list, start, midpoint - 1);
		node.right = createBalancedBST(list, midpoint + 1, end);
		return node;
	}
	
	//Outputs the indented tree 
	public void displayTree(Node node, int level) {
        if (node == null) return;

        for (int i = 0; i < level; i++)
            System.out.print("    ");
        System.out.println(node.element);
        displayTree(node.left, level + 1);
        displayTree(node.right, level + 1);
	}
	
	//Determines if it's a binary search tree
	public boolean isBST(Node node) {
		if(node == null)
			return true;
		else if(node.left != null && node.element < (node.left).element)
			return false;
		else if(node.right != null && node.element > (node.right).element)
			return false; 
		boolean left = isBST(node.left);
		boolean right = isBST(node.right);
		return left && right;
	} 
	
	//Determines if its a balanced tree
	public boolean isBalanced(Node node) {
		if(node ==  null)
			return false;
		int leftHeight = height(node.left);
		int rightHeight = height(node.right);
		int balanceFactor = Math.abs(leftHeight - rightHeight);
		if(balanceFactor == 1 || balanceFactor == 0)
			return true;
		else
			return false;
	}
	
	//Get the height of the bst
	public int height() {
		return height(root);
	}
	
	//height helper method
	public int height(Node root) {
		Node current = root;
		int left = 0, right = 0;
		
		if(current == null)
			return -1;
		left = height(current.left);
		right = height(current.right);
		if(left > right)
			return left + 1;
		else
			return right + 1;
	}
	
	//Returns an arraylist of values from the tree (use this in the second constructor?
	public ArrayList<Integer> toArray() {
		Node current = root;
		ArrayList<Integer> arr = new ArrayList<>();
		toArray(arr, current);
		
		//check if bst if not need to sort the array list 
		if(!isBST(root)) arr.sort(null);
		
		return arr;
	}
	
	public void toArray(ArrayList<Integer> arr, Node node){
		if(node != null) {
			toArray(arr, node.left);
			arr.add(node.element);
			toArray(arr, node.right);
		}
	}
	
	public Node getRoot() {
		return this.root;
	}
	
	private static class Node {
		private int element;
		private Node left;
		private Node right;
		
		//no-arg constructor
		private Node() {
			
		}
		
		private Node(Integer e) {
			this.element = e;
		}
	}
}
