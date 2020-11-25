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

public List<Integer> preorder() {
    List<Integer> list = new ArrayList<>();
    preorderHelper(root, list);
    return list;
}

private void preorderHelper(Node node, List<Integer> list) {
    if (node != null) {
        list.add(node.data);
        preorderHelper(node.left, list);
        preorderHelper(node.right, list);
    }
}

public List<Integer> postorder() {
    List<Integer> list = new ArrayList<>();
    preorderHelper(root, list);
    return list;
}

private void postorderHelper(Node node, List<Integer> list) {
    if (node != null) {
        preorderHelper(node.left, list);
        preorderHelper(node.right, list);
        list.add(node.data);
    }
}

