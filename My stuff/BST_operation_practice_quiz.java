
/* 
 * BRIEFLY, explain how to delete a value from the BST 
 * if the node has one child.  Try to explain the 
 * question both in terms of how it works on a diagram, and in code.
 * 
 * 
 * Create dummy node and call private helper method; 
 * traverse to the data to be deleted using pointer reinforcement; 
 * when data is found, store it in the dummy node 
 * and return the non-null child; return current node 
 * of each recursive call; once back to public method, 
 * return dummy node's data.
 *
 */



What is the best, worst, and average case Big O 
of searching for a value in the BST?


Best and average cases are O(log n) 
(the BST is well balanced); 
worst case is O(n) (BST is or is very similar to a LinkedList, 
so very unbalanced).