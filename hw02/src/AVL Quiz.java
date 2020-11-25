// AVL Quiz
/*
 * given a threshold integer, t, count the number of nodes
 * the tree has that are less than or equal to t.
 *
 */

public class BST {
	private class BSTNode {
	  int data; 
	  BSTNode left;
	  BSTNode right;
	}
	private BSTNode root;
	private int size;

	public int countThresh(int t) {
	   return countThresh(t, root);
	}

	private int countThresh(int t, BSTNode curr) {
		if (curr == null) {
       		return 0;
	    }
	    if (curr.data > t) {
	        return countThresh(t, curr.left);
	    } else if (curr.data == t) {
	        return 1 + countThresh(t, curr.left);
	    } else {
	        return 1 + countThresh(t, curr.left) + countThresh(t, curr.right);
	    }
	}
}