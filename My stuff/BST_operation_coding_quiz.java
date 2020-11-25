public class BST  {

    private Node root;
    private int size;

    // Node class is an inner class, so you can access instance variables directly.
    private class Node {
        private int data;
        private Node left;
        private Node right;
    }
}

public BST(Collection<Integer> data) {
    for (Integer t : data) {
        add(t);
    }
}

Hint: not all Collection's implement the get(int index) method, but all implement the Iterable interface.

public boolean contains(int data) {
    return containsHelper(data, root);
}

private boolean containsHelper(int data, Node node) {
    if (node == null) {
    	return false
    } else if (target > node.data) {
    	return containsHelper(data, node.right);
    }else if (target < node.data) {
    	return containsHelper(data, node.left);
    } else {
    	return true;
    }
    
}

public void printInOrder() {
    printInOrderHelper(root);
}

private void printInOrderHelper(Node node) {
    if (node != null) {
    	printInOrderHelper(node.left);
    	System.out.print(node.data + " ");
    	printInOrderHelper(node.right);
    }
}