/**
 * Removes data in the singly linked list recursively
 *
 * @param curr the node in the current recursive call, initially the head
 * @param data which is stored in the node
 * @return the node returned in the recursive call
 */
private Node rRemoveData(Node curr, int data) {

	if (curr == null) { 
		return null;
	} else {
		if (curr.data == data) {
			return curr.next;
		} else {
			curr.next = rRemoveData(curr.next, data);
			return curr;
		}
	}
	
}

/**
 * Determines if the linked list is sorted in ascending order, recursively 
 *
 * @param curr the node in the current recursive call, initially the head
 * @return whether the linked list is sorted in ascending order
 */
public boolean rSLLSorted(Node curr) {
	if (curr.next == null) {
		return true;
	}

	if (curr.data > curr.next.data) {
		return false
	} else {
		return rSLLSorted(curr.next);
	}
}

/**
 * Counts amount of nodes in singly linked list recursively
 *
 * @param curr the node in the current recursive call, initially the head
 * @return the count of nodes
 */
private int rCount(Node curr) {
	if (curr == null) {
		return 0;
	} else {

		return 1 + rCount(curr.next)
	}
}