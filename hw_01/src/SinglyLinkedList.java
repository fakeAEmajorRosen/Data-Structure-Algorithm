
/**
 * I worked on the homework assignment alone, using only course materials.
 *
 * Your implementation of a non-circular SinglyLinkedList with a tail pointer.
 *
 * @author Ting-Ying Yu
 * @version 1.0
 * @userid tyu304
 * @GTID 903534212
 */
import java.util.NoSuchElementException;
public class SinglyLinkedList<T> {

    // Do not add new instance variables or modify existing ones.
    private SinglyLinkedListNode<T> head;
    private SinglyLinkedListNode<T> tail;
    private int size;

    // Do not add a constructor.

    /**
     * Adds the element to the specified index.
     *
     * Must be O(1) for indices 0 and size and O(n) for all other cases.
     *
     * @param index the index to add the new element
     * @param data  the data to add
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index > size
     * @throws java.lang.IllegalArgumentException  if data is null
     */
    public void addAtIndex(int index, T data) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Can't insert element at "
                    + "negative index or index greater than the size.");
        } else if (data == null) {
            throw new IllegalArgumentException("Cannot insert null element.");
        } else if (index == 0) {
            addToFront(data);
        } else if (index == size) {
            addToBack(data);
        } else {
            SinglyLinkedListNode<T> current = head;
            SinglyLinkedListNode<T> newNode = new SinglyLinkedListNode<>(data);
            for (int i = 0; i <= index - 2; i++) {
                current = current.getNext();
            }
            newNode.setNext(current.getNext());
            // newNode.setPrevious(current);
            current.setNext(newNode);
            current.getNext().setPrevious(newNode)
            size++;
        }
    }

    /**
     * Adds the element to the front of the list.
     *
     * Must be O(1).
     *
     * @param data the data to add to the front of the list
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToFront(T data) {
        if (data == null) {
            throw new IllegalArgumentException("New data can not be null.");
        } else if (size == 0) {
            SinglyLinkedListNode<T> newNode = new SinglyLinkedListNode<>(data);
            head = newNode;
            tail = head;
            size++;
        } else {
            SinglyLinkedListNode<T> newNode = new SinglyLinkedListNode<>(data);
            newNode.setNext(head);
            head = newNode;
            size++;
        }
    }

    /**
     * Adds the element to the back of the list.
     *
     * Must be O(1).
     *
     * @param data the data to add to the back of the list
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToBack(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot add null element.");
        } else if (size == 0) {
            addToFront(data);
        } else {
            SinglyLinkedListNode<T> newNode = new SinglyLinkedListNode<>(data);
            tail.setNext(newNode);
            tail = newNode;
            size++;
        }
    }

    /**
     * Removes and returns the element at the specified index.
     *
     * Must be O(1) for indices 0 and size - 1 and O(n) for all other
     * cases.
     *
     * @param index the index of the element to remove
     * @return the data that was removed
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index >= size
     */
    public T removeAtIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Cannot remove element at "
                + "negative index or an index greater than the size.");
        } else if (index == 0) {
            return removeFromFront();
        } else if (index == size - 1) {
            return removeFromBack();
        } else {
            SinglyLinkedListNode<T> current = head;
            for (int i = 0; i <= index - 2; i++) {
                current = current.getNext();
            }
            SinglyLinkedListNode<T> removed = current.getNext();
            current.setNext(current.getNext().getNext());
            // current.getNext().getNext().setPrevious(current)
            size--;
            return removed.getData();
        }

    }

    /**
     * Removes and returns the first data of the list.
     *
     * Must be O(1).
     *
     * @return the data formerly located at the front of the list
     * @throws java.util.NoSuchElementException if the list is empty
     */
    public T removeFromFront() {
        if (isEmpty()) {
            throw new NoSuchElementException("Cannot remove element from an empty list.");
        }

        SinglyLinkedListNode<T> removed = getHead();

        if (size == 1) {
            head = null;
            tail = null;
            size--;
            return removed.getData();
        } else {
            head = head.getNext();
            head.
            size--;
            return removed.getData();
        }
    }

    /**
     * Removes and returns the last data of the list.
     *
     * Must be O(n).
     *
     * @return the data formerly located at the back of the list
     * @throws java.util.NoSuchElementException if the list is empty
     */
    public T removeFromBack() {
        if (isEmpty()) {
            throw new NoSuchElementException("Cannot remove element from an empty list.");
        } else if (size == 1) {
            return removeFromFront();
        } else {
            SinglyLinkedListNode<T> current = getHead();
            while (current.getNext().getNext() != null) {
                current = current.getNext();
            }
            current.setNext(null);
            SinglyLinkedListNode<T> removed = getTail();
            tail = current;
            size--;
            return removed.getData();
        }

    }

    /**
     * Returns the element at the specified index.
     *
     * Must be O(1) for indices 0 and size - 1 and O(n) for all other cases.
     *
     * @param index the index of the element to get
     * @return the data stored at the index in the list
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index >= size
     */
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Cannot access negative index "
                + "or the index exceeds the list's size.");
        } else if (index == 0) {
            return head.getData();
        } else if (index == size - 1) {
            return tail.getData();
        } else {
            SinglyLinkedListNode<T> current = head;
            for (int i = 0; i <= index - 1; i++) {
                current = current.getNext();
            }
            return current.getData();
        }
    }

    /**
     * Returns whether or not the list is empty.
     *
     * Must be O(1).
     *
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Clears the list.
     *
     * Clears all data and resets the size.
     *
     * Must be O(1).
     */
    public void clear() {
        if (size != 0) {
            head.setNext(null);
            head = null;
            tail = null;
            size = 0;
        }
    }

    /**
     * Removes and returns the last copy of the given data from the list.
     *
     * Do not return the same data that was passed in. Return the data that
     * was stored in the list.
     *
     * Must be O(n).
     *
     * @param data the data to be removed from the list
     * @return the data that was removed
     * @throws java.lang.IllegalArgumentException if data is null
     * @throws java.util.NoSuchElementException   if data is not found
     */
    public T removeLastOccurrence(T data) {
        if (data == null) { // Data is null
            throw new IllegalArgumentException("The entered data cannot be null.");
        } else if (isEmpty()) { // Empty list
            throw new NoSuchElementException("The list is empty thus cannot find any occurrence.");
        }

        SinglyLinkedListNode<T> oneBeforeRemoved = null;
        SinglyLinkedListNode<T> current = head;
        SinglyLinkedListNode<T> removed = null;

        // List with only one element
        if (size == 1) {
            if (current.getData() == data) {
                return removeFromFront();
            } else {
                throw new NoSuchElementException("Cannot find any occurrence.");
            }
        }

        // Majority cases: start searching from the second node
        while (current != null && current.getNext() != null) {
            if (current.getNext().getData() == data) {
                oneBeforeRemoved = current; // Found the node before the occurrence
            }
            current = current.getNext();
        }

        if (oneBeforeRemoved == null) {
            if (head.getData() == data) {
                // If the occurrence is the head(index 0; first node)
                return removeFromFront();
            } else {
                // Not found
                throw new NoSuchElementException("Cannot find any occurrence.");
            }
        }

        if (tail.getData() == data) {
            // If the occurrence is the tail
            return removeFromBack();
        }

        // Find the occurrence
        removed = oneBeforeRemoved.getNext();
        // Set the pointer to the node after the occurrence
        oneBeforeRemoved.setNext(removed.getNext());

        size--;
        return removed.getData();
    }

    /**
     * Returns an array representation of the linked list.
     *
     * Must be O(n) for all cases.
     *
     * @return the array of length size holding all of the data (not the
     * nodes) in the list in the same order
     */
    public T[] toArray() {
        T[] arr = (T[]) new Object[size];
        SinglyLinkedListNode<T> current = head;
        for (int i = 0; i < size; i++) {
            arr[i] = current.getData();
            current = current.getNext();
        }
        return arr;
    }

    /**
     * Returns the head node of the list.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the node at the head of the list
     */
    public SinglyLinkedListNode<T> getHead() {
        // DO NOT MODIFY!
        return head;
    }

    /**
     * Returns the tail node of the list.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the node at the tail of the list
     */
    public SinglyLinkedListNode<T> getTail() {
        // DO NOT MODIFY!
        return tail;
    }

    /**
     * Returns the size of the list.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the size of the list
     */
    public int size() {
        // DO NOT MODIFY!
        return size;
    }
}
