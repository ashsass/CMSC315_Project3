/*
 * Name: Ashlyn Sassaman
 * Due date: 2/20/24
 * Description: 
 */

import java.util.*;

public class BinaryTree {
	private Node root;
	private int currentIndex;
	
	//no-arg constructor for best practice
	public BinaryTree() {
		
	}
	
	//Constructor that takes a string and constructs a tree
	public BinaryTree(String userInput) {
		currentIndex = 0;
		root = createTree(userInput);
		displayTree(root, 0);
//		System.out.println(root.element);
	}
	
	//Construcor that takes in an AL of ints and makes a balanced tree 
	//Note: unsure the type to use for ArrayList (Integer? String?)
	public BinaryTree(ArrayList<Integer> bstList) {
		
	}
	
	private Node createTree(String s) {
		//Use currentIndex to move through the string and find the end index for node/subtrees
		//Use start as the beginning point to the index
		currentIndex = 0;
		int start = currentIndex;
//		System.out.println("1. currentIndex: " + currentIndex);
//		System.out.println("1. start: " + start);
		
		//Remove outer () if the string is greater than one character
		if(s.length() > 1) {
			s = s.substring(1, s.length() - 1);
		} else {
			return null;
		}
//		System.out.println(s);
		
		//Want to find the amount of string that is actually a number to set the new node
		//So from start to currentIndex will have the value for a new node
		while(currentIndex < s.length() && Character.isDigit(s.charAt(currentIndex))) {
			currentIndex++;
		}
		
//		System.out.println("2. currentIndex: " + currentIndex);
//		System.out.println("2. start: " + start);
		
		//Create a node if the portion of the string was a number
		int value = Integer.parseInt(s.substring(start, currentIndex));
		Node node = newNode(value);
		
		
		//Use stack to find the index for the left and right subtree
		//turn this into its own method?
		Stack<Character> stack = new Stack<>();
		currentIndex++; //skip the space
		start = currentIndex;
//		System.out.println("3. currentIndex: " + currentIndex);
//		System.out.println("3. start: " + start);
		for(int i = currentIndex; i < s.length(); i++) {
			if(s.charAt(i) == '(') {
				stack.push(s.charAt(i));
			}
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
		//System.out.print(currentIndex);
//		System.out.println("4. currentIndex: " + currentIndex);
//		System.out.println("4. start: " + start);
		if(start != currentIndex && currentIndex > start) {
			String leftSubstring = s.substring(start, currentIndex + 1);
			String rightSubstring = s.substring(currentIndex + 2);
//			System.out.println("Left substring is : " + leftSubstring);
//			System.out.println("Right substring is : " + rightSubstring);
			//System.out.printf("Start: %d, currentIndex: %d\n", start, currentIndex);
			node.left = createTree(leftSubstring);
			
			//System.out.printf("Start: %d, currentIndex: %d\n", start, currentIndex);
			//System.out.println("in the root left/right loop. Show the current index substring: " + s.substring(currentIndex));
			node.right = createTree(rightSubstring);
		}
			return node;
	}
	
	
	//Outputs the indented tree 
	public void displayTree(Node node, int level) {
        if (node == null) {
            return;
        }

        for (int i = 0; i < level; i++) {
            System.out.print("    ");
        }

        System.out.println(node.element);
        displayTree(node.left, level + 1);
        displayTree(node.right, level + 1);
	}
	
	//determines if it's a binary search tree
	public boolean isBST() {
		return false;
	}
	
	//maybe use another method to determine if the BST is balanced?
	public boolean isBalanced() {
		return false;
	}
	
	//get the height of the bst
	public int height() {
		return -1;
	}
	
//	public int height(TreeNode<E> root) {
//		return -1;
//	}
	
	//returns an arraylist of values from the tree (use this in the second constructor?
	//Again - unsure of what type the ArrayList should be? 
	public ArrayList<Integer> toArray() {
	
		return null;
	}
	
	private static Node newNode(int e) {
//		System.out.println("newNode called");
		return new Node(e);
	}
	
	private static class Node {
		int element;
		Node left;
		Node right;
		
		//no-arg constructor
		private Node() {
			
		}
		
		private Node(Integer e) {
			this.element = e;
		}
		
//		private void insert(Node node, Integer newEl) {
//			if(n)
//			if(newEl > node.element) {
//				if(node.rightChild != null) {
//					insert(node.rightChild, newEl);
//				}
//				else {
//					node.rightChild = newEl;
//				}
//			}
//			else if(newEl < node.element)
//		}
//		
//		private void setLeftChild(Node left) {
//			this.leftChild = left;
//		}
//		
//		private Node getLeftChild() {
//			return leftChild;
//		}
//		
//		private void setRightChild(Node right) {
//			this.rightChild = right;
//		}
//		private Node getRightChild() {
//			return rightChild;
//		}
	}
}
