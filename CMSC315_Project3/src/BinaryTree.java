/*
 * Name: Ashlyn Sassaman
 * Due date: 2/20/24
 * Description: 
 */

import java.util.*;
/*
 * NOTE: don't even worry about balance factor if tree is not a bst
 */

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
//		displayTree(root, 0);
//		System.out.println(root.element);
//		System.out.println("Is tree a bst? " + isBST(root));
//		System.out.println("Height of tree: " + height());
//		System.out.println("Is tree balanced: " + isBalanced(root));
		ArrayList<Integer> arr = toArray();
//		System.out.println(arr.size());
		for(Integer e: arr)
			System.out.print(e + " ");
		
	}
	
	//Construcor that takes in an AL of ints and makes a balanced tree 
	//Note: create a balanced tree from array list
	//How do i implement making it balanced? does that happen after i get the array list or should the process
	//of making the array list incorporate making it balanced and then i just redraw it in this method?
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
		if(s.length() > 1 && s.charAt(0) == '(') {
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
	//traverse the tree and if it passes the less than greater than its bst?
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
	
	//maybe use another method to determine if the BST is balanced?
	public boolean isBalanced(Node node) {
		if(node ==  null)
			return false;
		int leftHeight = height(node.left);
		int rightHeight = height(node.right);
		int balanceFactor = Math.abs(leftHeight - rightHeight);
//		System.out.printf("left height: %d, and right height: %d\n", leftHeight, rightHeight);
//		System.out.printf("Balance factor: %d\n", balanceFactor);
		if(balanceFactor == 1 || balanceFactor == 0)
			return true;
		else
			return false;
	}
	
	//get the height of the bst
	public int height() {
		return height(root);
	}
	
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
	
	//returns an arraylist of values from the tree (use this in the second constructor?
	//How to traverse the binary tree for the arraylist - preorder?
	//Also should it be balanced while making the array list or just create the arraylist then send it to
	//the new constructor 
	
	//make it an array list of nodes or integers?
	public ArrayList<Integer> toArray() {
		Node current = root;
		ArrayList<Integer> arr = new ArrayList<>();
		return toArray(arr, current);
	}
	
	public ArrayList<Integer> toArray(ArrayList<Integer> arr, Node node){
		if(node == null) return null;
//		System.out.println(node.element);
		arr.add(node.element);
//		System.out.println(arr.size());
		toArray(arr, node.left);
		toArray(arr, node.right);
		return arr;
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
