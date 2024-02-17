/*
 * Name: Ashlyn Sassaman
 * Due date: 2/20/24
 * Description: 
 */

import java.util.*;

public class BinaryTree {
	private Node root;
	
	//no-arg constructor for best practice
	public BinaryTree() {
		
	}
	
	//Constructor that takes a string and constructs a tree
	public BinaryTree(String userInput) {
		createTree(userInput);
	}
	
	//Construcor that takes in an AL of ints and makes a balanced tree 
	//Note: unsure the type to use for ArrayList (Integer? String?)
	public BinaryTree(ArrayList<Integer> bstList) {
		
	}
	
	private void createTree(String userInput) {
		//get rid of outside parantheses
		userInput = userInput.substring(1, userInput.length()-1);
		System.out.print(userInput);
		
		
	}
	
	
	//Outputs the indented tree 
	public void displayTree(String userInput) {
		
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
	
	private class Node {
		Integer element;
		Node leftChild;
		Node rightChild;
		
		//no-arg constructor
		private Node() {
			
		}
		
		private Node(Integer e) {
			this.element = e;
			this.leftChild = null;
			this.rightChild = null;
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
		private void setLeftChild(Node left) {
			this.leftChild = left;
		}
		
		private Node getLeftChild() {
			return leftChild;
		}
		
		private void setRightChild(Node right) {
			this.rightChild = right;
		}
		private Node getRightChild() {
			return rightChild;
		}
	}
}
