
public class SortedTree {
	public final static int SIZE_INPUT = 8;
	TreeNode root;	
	
	// Receives a sorted sequence of integers to build 
	// a binary tree. If the input is not sorted, then 
	// it will not build the binary tree.
	public SortedTree(int[] input) {
		root = null;
		
		// if input is not sorted return
		if(!verifyInput(input)) {	
			return;
		}
		// if input is sorted, build the tree from start to end
		else {
			root = buildTree(input, 0, input.length - 1);
		}
	}	
	
	// Builds a binary tree using the objects from 
	// input[start] to input[end].
	// Assumptions: the input sequence has been sorted
	private TreeNode buildTree(int[] input, int start, int end) {
		// if start position > end position (base case)
		if (start > end) {
			return null;
		}
		// else the start position <=  end position
		int middle = (start + end) / 2;
		TreeNode node = new TreeNode(input[middle]);
		 
		node.left = buildTree(input, start, middle - 1);
		node.right = buildTree(input, middle + 1, end);
		 
		return node;
	}
	
	// verify if the input is sorted in ascending order
	private boolean verifyInput(int [] input) {
		if(input == null) {
			return false;
		}
		
		for(int i = 0; i < input.length - 1; i++) {
			if(input[i] >= input[i + 1]) {
				return false;
			}
		}		
		return true;
	}
	
	// calls the _depth method
	public int depth() {
		return _depth(root);
	}
	
	// return the depth of the binary search tree
	public int _depth(TreeNode root) {
		// if the tree has no nodes (base case 1)
		if(root == null) {
			return -1;
		}
		// if the tree has only one node (base case 2)
		else if(root.left == null && root.right == null){
			return 0;
		}
		// if the tree has multiple nodes (recursion)
		else {
			int leftDepth = _depth(root.left);
			int rightDepth = _depth(root.right);
			
			if(leftDepth > rightDepth) {
				return (leftDepth++);
			}
			else {
				return (rightDepth++);
			}
		}
	}
	
	// calls the traverseNodes method
	public void traverse() {
		traverseNodes(root);
	}
	
	// print the binary search tree in ascending order (inorder)
	private void traverseNodes(TreeNode root) {
		if(root != null){
	        traverseNodes(root.left);  // traverse the left subtree
	        System.out.print(root.info + " ");  // visit the root
	        traverseNodes(root.right); //traverse the right subtree
	    }
	}
	
	public static void main(String args[]) {
		
		int[] input = new int[SIZE_INPUT];
		for(int i = 0; i < SIZE_INPUT; i++) {			
			input[i] = i;
		}
		// another input array we saw in class:
		// int[] input = {4, 9, 15, 20, 22, 24, 35, 87};

		// create the binary search tree given the sorted input 
		SortedTree st = new SortedTree(input);
		
		// print its depth
		System.out.println("The depth of the tree is " + st.depth());
		
		// print the tree nodes in ascending order
		st.traverse();
	}
}
