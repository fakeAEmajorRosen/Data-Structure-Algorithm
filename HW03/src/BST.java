import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;
/**
 * Your implementation of a BST.
 *
 * @author Ting-Ying Yu
 * @version 1.0
 * @userid tyu304
 * @GTID 903534212
 *
 * Collaborators: I work on this assignment by myself.
 *
 * Resources: I work on the assignment using only the course materials.
 */
public class BST<T extends Comparable<? super T>> {

    /*
     * Do not add new instance variables or modify existing ones.
     */
    private BSTNode<T> root;
    private int size;

    /**
     * Constructs a new BST.
     *
     * This constructor should initialize an empty BST.
     *
     * Since instance variables are initialized to their default values, there
     * is no need to do anything for this constructor.
     */
    public BST() {
        // DO NOT IMPLEMENT THIS CONSTRUCTOR!
    }

    /**
     * Constructs a new BST.
     *
     * This constructor should initialize the BST with the data in the
     * Collection. The elements should be added to the BST in the order in 
     * which they appear in the Collection.
     *
     * Hint: Not all Collections are indexable like Lists, so a regular for-loop
     * will not work here. However, all Collections are Iterable, so what type
     * of loop would work?
     *
     * @param data the data to add
     * @throws java.lang.IllegalArgumentException if data or any element in data
     *                                            is null
     */
    public BST(Collection<T> data) {
        if (data == null) {
            throw new IllegalArgumentException("Invalid Data. (Data can't be null)");
        }
        for (T datam: data) {
            if (datam == null) {
                throw new IllegalArgumentException("Invalid Data. (Data can't be null)");
            } else {
                add(datam);
            }
        }
    }

    /**
     * Adds the data to the tree.
     *
     * The data becomes a leaf in the tree.
     *
     * Traverse the tree to find the appropriate location. If the data is
     * already in the tree, then nothing should be done (the duplicate
     * shouldn't get added, and size should not be incremented).
     *
     * Must be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data the data to add
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Invalid Data. (Data can't be null)");
        } else if (root == null) {
            root = new BSTNode<T>(data);
            size++;
        } else {
            root = addHelper(data, root);
        }
    }

    /**
     * Helper method for add method
     * @param curr current node to add data to
     * @param data the data to add
     * @return the node that is passed in.
     */
    private BSTNode<T> addHelper(T data, BSTNode<T> curr) {
        if (curr == null) {
            size++;
            return new BSTNode<T>(data);
        }
        if (data.compareTo(curr.getData()) < 0) {
            curr.setLeft(addHelper(data, curr.getLeft()));
        } else if (data.compareTo(curr.getData()) > 0) {
            curr.setRight(addHelper(data, curr.getRight()));
        }
        return curr;
    }

    /**
     * Removes and returns the data from the tree matching the given parameter.
     *
     * There are 3 cases to consider:
     * 1: The node containing the data is a leaf (no children). In this case,
     * simply remove it.
     * 2: The node containing the data has one child. In this case, simply
     * replace it with its child.
     * 3: The node containing the data has 2 children. Use the predecessor to
     * replace the data. You MUST use recursion to find and remove the
     * predecessor (you will likely need an additional helper method to
     * handle this case efficiently).
     *
     * Do not return the same data that was passed in. Return the data that
     * was stored in the tree.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * Must be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data the data to remove
     * @return the data that was removed
     * @throws java.lang.IllegalArgumentException if data is null
     * @throws java.util.NoSuchElementException   if the data is not in the tree
     */
    public T remove(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Invalid Data. (Data can't be null)");
        } else if (root == null) {
            // If there is no data
            throw new NoSuchElementException("Data not found");
        } else {
            // Normal case
            BSTNode<T> dummy = new BSTNode<T>(null);
            root = removeHelper(root, data, dummy);
            size--;
            return dummy.getData();
        }
    }
    /**
     * Helper method that uses recursion to remove the data from the tree.
     *
     * @param curr the current node.
     * @param data the data that is going to be removed.
     * @param dummy the node that holds the data of the removed node.
     * @return the node that is passed in
     * @throws NoSuchElementException if the data is not found in the tree
     */
    private BSTNode<T> removeHelper(BSTNode<T> curr, T data, BSTNode<T> dummy) {
        if (curr == null) {
            throw new NoSuchElementException("Data not found.");
        }
        if (curr.getData().equals(data)) {
            // Case for root == target
            dummy.setData(curr.getData());
            if (curr.getRight() == null && curr.getLeft() == null) {
                return null;
            } else if (curr.getLeft() == null) {
                return curr.getRight();
            } else if (curr.getRight() == null) {
                return curr.getLeft();
            } else {
                BSTNode<T> dummy2 = new BSTNode<T>(curr.getData());
                curr.setLeft(removePredecessor(curr.getLeft(), dummy2));
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
        return curr;
    }
    /**
     * Helper method that finds the predecessor node and stores its data
     * using recursion.
     *
     * @param curr the current node.
     * @param dummy the node that stores the data of the predecessor node.
     * @return the node that is passed in
     */
    private BSTNode<T> removePredecessor(BSTNode<T> curr, BSTNode<T> dummy) {
        if (curr.getRight() == null) {
            dummy.setData(curr.getData());
            return curr.getLeft();
        } else {
            curr.setRight(removePredecessor(curr.getRight(), dummy));
            return curr;
        }
    }



    /**
     * Returns the data from the tree matching the given parameter.
     *
     * Do not return the same data that was passed in. Return the data that
     * was stored in the tree.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * Must be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data the data to search for
     * @return the data in the tree equal to the parameter
     * @throws java.lang.IllegalArgumentException if data is null
     * @throws java.util.NoSuchElementException   if the data is not in the tree
     */
    public T get(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Invalid Data. (Data can't be null)");
        } else if (root == null) {
            throw new java.util.NoSuchElementException("Data not found.");
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
    private T getHelper(BSTNode<T> curr, T data) {
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
     * Must be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data the data to search for
     * @return true if the parameter is contained within the tree, false
     * otherwise
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public boolean contains(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Invalid Data. (Data can't be null)");
        }

        try {
            getHelper(root, data);
        } catch (NoSuchElementException e) {
            return false;
        }
        return true;
    }

    /**
     * Generate a pre-order traversal of the tree.
     *
     * Must be O(n).
     *
     * @return the preorder traversal of the tree
     */
    public List<T> preorder() {
        return preorderHelper(root, new ArrayList<T>());
    }

    /**
     * Helper method for pre-order traversal (preorder())
     * @param curr current node that is traversing.
     * @param list the traversal list.
     * @return the preorder traversal list of the tree.
     */
    private List<T> preorderHelper(BSTNode<T> curr, List<T> list) {
        if (curr == null) {
            return list;
        } else {
            list.add(curr.getData());
            if (curr.getLeft() != null) {
                list = preorderHelper(curr.getLeft(), list);
            }
            if (curr.getRight() != null) {
                list = preorderHelper(curr.getRight(), list);
            }
        }
        return list;

    }

    /**
     * Generate an in-order traversal of the tree.
     *
     * Must be O(n).
     *
     * @return the inorder traversal of the tree
     */
    public List<T> inorder() {
        return inorderHelper(root, new ArrayList<T>());
    }

    /**
     * Helper method for in-order traversal (inorder())
     * @param curr current node that is traversing.
     * @param list the traversal list.
     * @return the inorder traversal list of the tree.
     */
    private List<T> inorderHelper(BSTNode<T> curr, List<T> list) {
        if (curr == null) {
            return list;
        }
        if (curr.getLeft() != null) {
            list = inorderHelper(curr.getLeft(), list);
        }
        list.add(curr.getData());
        if (curr.getRight() != null) {
            list = inorderHelper(curr.getRight(), list);
        }
        return list;
    }

    /**
     * Generate a post-order traversal of the tree.
     *
     * Must be O(n).
     *
     * @return the postorder traversal of the tree
     */
    public List<T> postorder() {
        return postorderHelper(root, new ArrayList<T>());
    }
    /**
     * Helper method for post-order traversal (postorder())
     * @param curr current node that is traversing.
     * @param list the traversal list.
     * @return the postorder traversal list of the tree.
     */
    private List<T> postorderHelper(BSTNode<T> curr, List<T> list) {
        if (curr != null) {
            postorderHelper(curr.getLeft(), list);
            postorderHelper(curr.getRight(), list);
            list.add(curr.getData());
        }
        return list;
    }


    /**
     * Generate a level-order traversal of the tree.
     *
     * This does not need to be done recursively.
     *
     * Hint: You will need to use a queue of nodes. Think about what initial
     * node you should add to the queue and what loop / loop conditions you
     * should use. You may import java.util.Queue as well as an implmenting
     * class.
     *
     * Must be O(n).
     *
     * @return the level order traversal of the tree
     */
    public List<T> levelorder() {
        List<T> list = new ArrayList<T>();
        Queue<BSTNode<T>> queue = new LinkedList<BSTNode<T>>();
        if (root == null) {
            return list;
        }
        queue.add(root);
        while (!queue.isEmpty()) {
            BSTNode<T> curr = queue.remove();
            list.add(curr.getData());
            if (curr.getLeft() != null) {
                queue.add(curr.getLeft());
            }
            if (curr.getRight() != null) {
                queue.add(curr.getRight());
            }
        }
        return list;
    }

    /**
     * Returns the height of the root of the tree.
     *
     * A node's height is defined as max(left.height, right.height) + 1. A
     * leaf node has a height of 0 and a null child has a height of -1.
     *
     * Must be O(n).
     *
     * @return the height of the root of the tree, -1 if the tree is empty
     */
    public int height() {
        return heightHelper(root, 0);
    }
    /**
     * Helper method for height()
     * @param curr current node.
     * @param h current height.
     * @return return height of the tree given its root.
     */
    private int heightHelper(BSTNode<T> curr, int h) {
        if (curr == null) {
            return -1;
        }
        int rH = h;
        int lH = h;
        if (curr.getRight() != null) {
            rH = heightHelper(curr.getRight(), h + 1);
        }
        if (curr.getLeft() != null) {
            lH = heightHelper(curr.getLeft(), h + 1);
        }
        return Math.max(rH, lH);

    }

    /**
     * Clears the tree.
     *
     * Clears all data and resets the size.
     *
     * Must be O(1).
     */
    public void clear() {
        size = 0;
        root = null;
    }

    /**
     * Finds the path between two elements in the tree, specifically the path
     * from data1 to data2, inclusive of both.
     *
     * To do this, you must first find the deepest common ancestor of both data
     * and add it to the list. Then traverse to data1 while adding its ancestors
     * to the front of the list. Finally, traverse to data2 while adding its
     * ancestors to the back of the list. Please note that there is no
     * relationship between the data parameters in that they may not belong
     * to the same branch. You will most likely have to split off and
     * traverse the tree for each piece of data.
     * *
     * You may only use 1 list instance to complete this method. Think about
     * what type of list to use since you will have to add to the front and
     * back of the list.
     *
     * This method only needs to traverse to the deepest common ancestor once.
     * From that node, go to each data in one traversal each. Failure to do
     * so will result in a penalty.
     *
     * If both data1 and data2 are equal and in the tree, the list should be
     * of size 1 and contain the element from the tree equal to data1 and data2.
     *
     * Hint: How can we use the order property of the BST to locate the deepest
     * common ancestor?
     *
     * Ex:
     * Given the following BST composed of Integers
     *              50
     *          /        \
     *        25         75
     *      /    \
     *     12    37
     *    /  \    \
     *   11   15   40
     *  /
     * 10
     * findPathBetween(10, 40) should return the list [10, 11, 12, 25, 37, 40]
     * findPathBetween(50, 37) should return the list [50, 25, 37]
     * findPathBetween(75, 75) should return the list [75]
     *
     * Must be O(log n) for a balanced tree and O(n) for worst case.
     *
     * @param data1 the data to start the path from
     * @param data2 the data to end the path on
     * @return the unique path between the two elements
     * @throws java.lang.IllegalArgumentException if either data1 or data2 is
     *                                            null
     * @throws java.util.NoSuchElementException   if data1 or data2 is not in
     *                                            the tree
     */
    public List<T> findPathBetween(T data1, T data2) {
    	if (root == null) {
    		// If the tree is empty
    		throw new NoSuchElementException("Not found");
    	}
        if (data1 == null || data2 == null) {
            throw new IllegalArgumentException("Invalid data. (Data can't be null)");
        }
        // The list that store the path
        LinkedList<T> list = new LinkedList<T>();

        // Find the deepest common ancestor by using a helper function
        BSTNode<T> ancestor = findDCAHelper(root, data1, data2);

        list.add(ancestor.getData());

        // Traverse to data1 while adding its ancestors to the front of the list.
        if (data1.compareTo(ancestor.getData()) > 0) {
            traverseHelper1(ancestor.getRight(), list, data1);
        } else if (data1.compareTo(ancestor.getData()) < 0) {
            traverseHelper1(ancestor.getLeft(), list, data1);
        }

        // Traverse to data2 while adding its ancestors to the back of the list.
        if (data2.compareTo(ancestor.getData()) > 0) {
            traverseHelper2(ancestor.getRight(), list, data2);
        } else if (data2.compareTo(ancestor.getData()) < 0) {
            traverseHelper2(ancestor.getLeft(), list, data2);
        }

        return list;
    }
    /**
     * Helper method for findPathBetween() to find the deepest common ancestor of both data
     * @param node current node(the root).
     * @param data1 data1 from the findPathBetween() input.
     * @param data2 data2 from the findPathBetween() input.
     * @return return the deepest common ancestor node
     */
    private BSTNode<T> findDCAHelper(BSTNode<T> node, T data1, T data2) {
        // If both n1 and n2 are smaller than root, then DCA lies in left
        if (node.getData().compareTo(data1) > 0 && node.getData().compareTo(data2) > 0) {
        	if (node.getLeft() != null) {
        		return findDCAHelper(node.getLeft(), data1, data2);
        	}
            
        }
        // If both n1 and n2 are greater than root, then DCA lies in right
        if (node.getData().compareTo(data2) < 0 && node.getData().compareTo(data1) < 0) {
        	if (node.getRight() != null) {
        		return findDCAHelper(node.getRight(), data1, data2);
        	}
            
        }
        // Else if node is equal to either of them or is between them,
        // return the root
        return node;
    }

    /**
     * Helper method for findPathBetween() to traverse to data1
     * @param curr current node.
     * @param list the list that stores the path.
     * @param data1 data1 from the findPathBetween() input.
     */
    private void traverseHelper1(BSTNode<T> curr, LinkedList<T> list, T data1) {
        list.addFirst(curr.getData());
        if (data1.compareTo(curr.getData()) == 0) {
            return;
        } else if (data1.compareTo(curr.getData()) > 0) {
            if (curr.getRight() == null) {
                throw new NoSuchElementException("Data not found");
            }
            traverseHelper1(curr.getRight(), list, data1);
        } else {
            if (curr.getLeft() == null) {
                throw new NoSuchElementException("Data not found");
            }
            traverseHelper1(curr.getLeft(), list, data1);
        }


    }

    /**
     * Helper method for findPathBetween() to traverse to data2
     * @param curr current node.
     * @param list the list that stores the path.
     * @param data data2 from the findPathBetween() input.
     */
    private void traverseHelper2(BSTNode<T> curr, LinkedList<T> list, T data) {
        list.addLast(curr.getData());
        if (data.compareTo(curr.getData()) == 0) {
            return;
        } else if (data.compareTo(curr.getData()) > 0) {
            if (curr.getRight() == null) {
                throw new NoSuchElementException("Data not found");
            }
            traverseHelper2(curr.getRight(), list, data);
        } else {
            if (curr.getRight() == null) {
                throw new NoSuchElementException("Data not found");
            }
            traverseHelper2(curr.getLeft(), list, data);
        }
    }


    /**
     * Returns the root of the tree.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the root of the tree
     */
    public BSTNode<T> getRoot() {
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
}
