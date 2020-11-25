import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.ArrayList;

/**
 * Your implementation of an AVL.
 *
 * @author Ting-Ying Yu
 * @version 1.0
 * @userid tyu304
 * @GTID 903534212
 *
 * Collaborators: I work on this assignment alone.
 *
 * Resources: I only use the course materials.
 */
public class AVL<T extends Comparable<? super T>> {

    /*
     * Do not add new instance variables or modify existing ones.
     */
    private AVLNode<T> root;
    private int size;

    /**
     * Constructs a new AVL.
     *
     * This constructor should initialize an empty AVL.
     *
     * Since instance variables are initialized to their default values, there
     * is no need to do anything for this constructor.
     */
    public AVL() {
        // DO NOT IMPLEMENT THIS CONSTRUCTOR!
    }

    /**
     * Constructs a new AVL.
     *
     * This constructor should initialize the AVL with the data in the
     * Collection. The data should be added in the same order it is in the
     * Collection.
     *
     * @param data the data to add to the tree
     * @throws java.lang.IllegalArgumentException if data or any element in data
     *                                            is null
     */
    public AVL(Collection<T> data) {
        if (data == null) {
            throw new IllegalArgumentException("The input data cannot be null.");
        }
        for (T datam : data) {
            if (datam == null) {
                throw new IllegalArgumentException("Data cannot be null.");
            } else {
                add(datam);
            }
        }
    }

    /**
     * Adds the element to the tree.
     *
     * Start by adding it as a leaf like in a regular BST and then rotate the
     * tree as necessary.
     *
     * If the data is already in the tree, then nothing should be done (the
     * duplicate shouldn't get added, and size should not be incremented).
     *
     * Remember to recalculate heights and balance factors while going back
     * up the tree after adding the element, making sure to rebalance if
     * necessary.
     *
     * @param data the data to add
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException("The input data cannot be null.");
        }
        if (size == 0) {
            // Adding the first node
            root = new AVLNode<>(data);
            update(root);
            size++;
            return;
        }
        root = addHelper(data, root);
    }

    /**
     * A helper method for add()
     *
     * @param data the data to search for in the tree
     * @param curr current node to add the data to
     * @return the node that is passed in
     */
    private AVLNode<T> addHelper(T data, AVLNode<T> curr) {
        if (curr == null) {
            size++;
            return new AVLNode<T>(data);
        }
        if (data.compareTo(curr.getData()) < 0) {
            curr.setLeft(addHelper(data, curr.getLeft()));
        } else if (data.compareTo(curr.getData()) > 0) {
            curr.setRight(addHelper(data, curr.getRight()));
        }
        // balance method
        return balance(curr);
    }

    /**
     * A method that balances the tree with the input node as the root.
     * The height and balanceFactor are updated during rotations.
     * @param node root of subtree to be balanced
     * @return the root of the balanced subtree. Return null if node is null.
     */
    private AVLNode<T> balance(AVLNode<T> node) {
        if (node == null) {
            return null;
        }
        update(node);
        if (node.getBalanceFactor() == 2) {
            if (node.getLeft().getBalanceFactor() == 0 || node.getLeft().getBalanceFactor() == 1) {
                node = rightRotation(node);
            } else if (node.getLeft().getBalanceFactor() == -1) {
                node = leftRightRotation(node);
            }
        } else if (node.getBalanceFactor() == -2) {
            if (node.getRight().getBalanceFactor() == 0 || node.getRight().getBalanceFactor() == -1) {
                node = leftRotation(node);
            } else if (node.getRight().getBalanceFactor() == 1) {
                node = rightLeftRotation(node);
            }
        }
        return node;
    }

    /**
     * A method that updates height.
     * @param node the node that its height is going to be calculated
     */
    private void update(AVLNode<T> node) {
        if (node == null) {
            return;
        }

        int leftH = 0;
        int rightH = 0;

        if (node.getLeft() == null) {
            leftH = -1;
        } else {
            leftH = node.getLeft().getHeight();
        }

        if (node.getRight() == null) {
            rightH = -1;
        } else {
            rightH = node.getRight().getHeight();
        }

        node.setHeight(Math.max(leftH, rightH) + 1);
        node.setBalanceFactor(leftH - rightH);
    }

    /**
     * Removes and returns the element from the tree matching the given
     * parameter.
     *
     * There are 3 cases to consider:
     * 1: The node containing the data is a leaf (no children). In this case,
     * simply remove it.
     * 2: The node containing the data has one child. In this case, simply
     * replace it with its child.
     * 3: The node containing the data has 2 children. Use the successor to
     * replace the data, NOT predecessor. As a reminder, rotations can occur
     * after removing the successor node. Do NOT use the provided public 
     * predecessor method to remove a 2-child node.
     *
     * Remember to recalculate heights and balance factors while going back
     * up the tree after removing the element, making sure to rebalance if
     * necessary.
     *
     * Do not return the same data that was passed in. Return the data that
     * was stored in the tree.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * @param data the data to remove
     * @return the data that was removed
     * @throws java.lang.IllegalArgumentException if the data is null
     * @throws java.util.NoSuchElementException   if the data is not found
     */
    public T remove(T data) {
        if (data == null) {
            throw new IllegalArgumentException("The input data cannot be null.");
        } else if (root == null) {
            // If there is no data
            throw new NoSuchElementException("Data not found");
        } else {
            AVLNode<T> dummy = new AVLNode<T>(null);
            root = removeHelper(root, data, dummy);
            size--;
            return dummy.getData();
        }
    }

    /**
     * A helper method for remove()
     *
     * @param curr the current node
     * @param data the data to search for in the tree
     * @param dummy the node that holds the data of the removed node.
     * @return the node that is passed in
     * @throws NoSuchElementException if the data is not found in the tree
     */
    private AVLNode<T> removeHelper(AVLNode<T> curr, T data, AVLNode<T> dummy) {
        if (curr == null) {
            throw new NoSuchElementException("Data not found.");
        }
        if (curr.getData().equals(data)) {
            // Case for root == target
            dummy.setData(curr.getData());
            if (curr.getRight() == null && curr.getLeft() == null) {
                // the root has no child
                return null;
            } else if (curr.getLeft() == null) {
                // the root has right child
                return curr.getRight();
            } else if (curr.getRight() == null) {
                // the root has left child
                return curr.getLeft();
            } else {
                // the root have 2 children: get the successor
                AVLNode<T> dummy2 = new AVLNode<T>(curr.getData());
                curr.setRight(removeSuccessor(curr.getRight(), dummy2));
                curr.setData(dummy2.getData());
            }
        } else if (data.compareTo(curr.getData()) > 0) {
            if (curr.getRight() == null) {
                throw new NoSuchElementException("Data not found.");
            }
            curr.setRight(removeHelper(curr.getRight(), data, dummy));
        } else if (data.compareTo(curr.getData()) < 0) {
            if (curr.getLeft() == null) {
                throw new NoSuchElementException("Data not found.");
            }
            curr.setLeft(removeHelper(curr.getLeft(), data, dummy));
        }
        // balance AVL:
        curr = balance(curr);
        return curr;
    }

    /**
     * A helper method to help find the successor and stores its data
     * using recursion.
     *
     * @param curr the current node
     * @param dummy the node that holds the data of the removed node.
     * @return the node that is passed in
     */
    private AVLNode<T> removeSuccessor(AVLNode<T> curr, AVLNode<T> dummy) {
        if (curr.getLeft() == null) {
            dummy.setData(curr.getData());
            return curr.getRight();
        } else {
            curr.setLeft(removeSuccessor(curr.getLeft(), dummy));
            // balance
            curr = balance(curr);
            return curr;
        }
    }

    /**
     * Returns the element from the tree matching the given parameter.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * Do not return the same data that was passed in. Return the data that
     * was stored in the tree.
     *
     * @param data the data to search for in the tree
     * @return the data in the tree equal to the parameter
     * @throws java.lang.IllegalArgumentException if data is null
     * @throws java.util.NoSuchElementException   if the data is not in the tree
     */
    public T get(T data) {
        if (data == null) {
            throw new IllegalArgumentException("The input data cannot be null.");
        } else if (root == null) {
            throw new NoSuchElementException("Data not found.");
        }
        return getHelper(root, data);
    }

    /**
     * Helper method for get(data).
     *
     * @param curr the current node.
     * @param data the data to search for.
     * @return data in the tree equal to the parameter or a self call.
     * @throws java.util.NoSuchElementException   if the data is not in the tree
     */
    private T getHelper(AVLNode<T> curr, T data) {
        if (curr == null) {
            throw new NoSuchElementException("Data not found.");
        }
        if (data.compareTo(curr.getData()) == 0) {
            return curr.getData();
        } else if (data.compareTo(curr.getData()) > 0) {
            return getHelper(curr.getRight(), data);
        } else {
            return getHelper(curr.getLeft(), data);
        }
    }

    /**
     * Returns whether or not data matching the given parameter is contained
     * within the tree.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * @param data the data to search for in the tree
     * @return true if the parameter is contained within the tree, false
     * otherwise
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public boolean contains(T data) {
        if (data == null) {
            throw new IllegalArgumentException("The input data cannot be null.");
        }
        try {
            getHelper(root, data);
        } catch (NoSuchElementException e) {
            return false;
        }
        return true;
    }

    /**
     * Returns the height of the root of the tree. Do NOT compute the height
     * recursively. This method should be O(1).
     *
     * @return the height of the root of the tree, -1 if the tree is empty
     */
    public int height() {
        if (root == null) {
            return -1;
        } else {
            return root.getHeight();
        }
    }

    /**
     * Clears the tree.
     *
     * Clears all data and resets the size.
     */
    public void clear() {
        root = null;
        size = 0;
    }

    /**
     * In your BST homework, you worked with the concept of the predecessor, the
     * largest data that is smaller than the current data. However, you only
     * saw it in the context of the 2-child remove case.
     *
     * This method should retrieve (but not remove) the predecessor of the data
     * passed in. There are 3 cases to consider:
     * 1: The left subtree is non-empty. In this case, the predecessor is the
     * rightmost node of the left subtree.
     * 2: The left subtree is empty. In this case, the predecessor is the deepest
     * ancestor of the node containing data whose right child is also
     * an ancestor of data.
     * 3: If the data passed in is the minimum data in the tree, return null.
     *
     * This should NOT be used in the remove method.
     *
     * Ex:
     * Given the following AVL composed of Integers
     *     76
     *   /    \
     * 34      90
     *  \    /
     *  40  81
     * predecessor(76) should return 40
     * predecessor(81) should return 76
     *
     * @param data the data to find the predecessor of
     * @return the predecessor of data. If there is no smaller data than the
     * one given, return null.
     * @throws java.lang.IllegalArgumentException if the data is null
     * @throws java.util.NoSuchElementException   if the data is not in the tree
     */
    public T predecessor(T data) {
        if (data == null) {
            throw new IllegalArgumentException("The input data cannot be null.");
        } else if (root == null) {
            throw new NoSuchElementException("Data not found.");
        }
        // use a dummy node to keep track of the predecessor at any given time
        AVLNode<T> dummy = new AVLNode<T>(null);
        AVLNode<T> out = predecessorHelper(root, data, dummy);
        if (out == null) {
            return null;
        }
        return out.getData();
    }

    /**
     * A helper method for predecessor(data).
     *
     * @param curr the current node.
     * @param data the data to search for.
     * @param dummy the node that holds the predecessor.
     * @return the predecessor of the data
     * @throws java.util.NoSuchElementException   if the data is not in the tree
     */
    private AVLNode<T> predecessorHelper(AVLNode<T> curr, T data, AVLNode<T> dummy) {
        if (curr == null) {
            throw new java.util.NoSuchElementException("Data not found.");
        }
        if (data.compareTo(curr.getData()) == 0) {
            // When we found the target
            // Case 1: if the node has a left subtree
            // (also pass in the dummy node)
            if (curr.getLeft() != null) {
                dummy = rightmostLeftSubtree(curr.getLeft(), dummy);
            }
        } else if (data.compareTo(curr.getData()) > 0) {
            // update the dummy node when the current data is smaller than the target
            // since it might be the "predecessor"
            // handle Case 2 and Case 3
            dummy = curr;
            return predecessorHelper(curr.getRight(), data, dummy);
        } else if (data.compareTo(curr.getData()) < 0) {
            return predecessorHelper(curr.getLeft(), data, dummy);
        }
        return dummy;
    }

    /**
     * A helper method for to keep traversing until it get to the rightmost
     * node of the left subtree that we input in the predecessorHelper()
     * method.
     *
     * @param curr the current node.
     * @param dummy the node that holds the predecessor.
     * @return the predecessor node.
     */
    private AVLNode<T> rightmostLeftSubtree(AVLNode<T> curr, AVLNode<T> dummy) {
        if (curr.getRight() == null) {
            // update the dummy node until find it
            dummy = curr;
            return dummy;
        } else {
            return rightmostLeftSubtree(curr.getRight(), dummy);
        }
    }

    /**
     * Finds and retrieves the k-smallest elements from the AVL in sorted order,
     * least to greatest.
     *
     * In most cases, this method will not need to traverse the entire tree to
     * function properly, so you should only traverse the branches of the tree
     * necessary to get the data and only do so once. Failure to do so will
     * result in an efficiency penalty.
     *
     * Ex:
     * Given the following AVL composed of Integers
     *              50
     *            /    \
     *         25      75
     *        /  \     / \
     *      13   37  70  80
     *    /  \    \      \
     *   12  15    40    85
     *  /
     * 10
     * kSmallest(0) should return the list []
     * kSmallest(5) should return the list [10, 12, 13, 15, 25].
     * kSmallest(3) should return the list [10, 12, 13].
     *
     * @param k the number of smallest elements to return
     * @return sorted list consisting of the k smallest elements
     * @throws java.lang.IllegalArgumentException if k < 0 or k > n, the number
     *                                            of data in the AVL
     */
    public List<T> kSmallest(int k) {
        if (k < 0 || k > size) {
            throw new IllegalArgumentException("Input k is invalid(less than 0 or more than the AVL).");
        }
        List<T> list = new ArrayList<T>();
        if (k == 0) {
            return list;
        }
        return kSmallestHelper(root, list, k);

    }

    /**
     * Helper method for kSmallest().
     * @param curr current node that is traversing.
     * @param list the list that keep track of the smallest numbers.
     * @param k the number of smallest elements to return.
     * @return sorted list consisting of the k smallest elements
     */
    private List<T> kSmallestHelper(AVLNode<T> curr, List<T> list, int k) {
        if (curr != null) {
            if (list.size() < k) {
                list = kSmallestHelper(curr.getLeft(), list, k);
            }
            if (list.size() < k) {
                list.add(curr.getData());
            }
            if (list.size() < k) {
                list = kSmallestHelper(curr.getRight(), list, k);
            }
        }
        return list;
    }

    /**
     * Returns the root of the tree.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the root of the tree
     */
    public AVLNode<T> getRoot() {
        // DO NOT MODIFY THIS METHOD!
        return root;
    }

    /**
     * Returns the size of the tree.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the size of the tree
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }

    // All the other private helper Methods:
    /**
     * Performs left-rotation on specified node
     * @param node specified node to perform left-rotation on
     * @return new subtree after performing left-rotation
     */
    private AVLNode<T> leftRotation(AVLNode<T> node) {
        AVLNode<T> nodeB = node.getRight();
        node.setRight(nodeB.getLeft());
        nodeB.setLeft(node);
        // Update A's H, BF
        update(node);
        // Update B
        update(nodeB);
        return nodeB;
    }

    /**
     * Performs right-rotation on specified node
     * @param node specified node to perform right-rotation on
     * @return new subtree after performing right-rotation
     */
    private AVLNode<T> rightRotation(AVLNode<T> node) {
        AVLNode<T> nodeB = node.getLeft();
        node.setLeft(nodeB.getRight());
        nodeB.setRight(node);
        // Update C
        update(node);
        // Update B
        update(nodeB);
        return nodeB;
    }

    /**
     * Performs right-left-rotation on specified node
     * @param node specified node to perform right-left-rotation on
     * @return new subtree after performing right-left-rotation
     */
    private AVLNode<T> rightLeftRotation(AVLNode<T> node) {
        node.setRight(rightRotation(node.getRight()));
        return leftRotation(node);
    }

    /**
     * Performs left-right-rotation on specified node
     * @param node specified node to perform left-right-rotation on
     * @return new subtree after performing left-right-rotation
     */
    private AVLNode<T> leftRightRotation(AVLNode<T> node) {
        node.setLeft(leftRotation(node.getLeft()));
        return rightRotation(node);
    }
}
