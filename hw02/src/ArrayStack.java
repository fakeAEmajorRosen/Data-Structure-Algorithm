/**
 * Your implementation of an ArrayStack.
 *
 * @author Ting-Ying Yu
 * @version 1.0
 * @userid tyu304
 * @GTID 903534212
 *
 * Collaborators: I work on this homework by myself.
 *
 * Resources: I work on this homework only using the course resources.
 */
import java.util.NoSuchElementException;
public class ArrayStack<T> {

    /*
     * The initial capacity of the ArrayStack.
     *
     * DO NOT MODIFY THIS VARIABLE.
     */
    public static final int INITIAL_CAPACITY = 9;

    /*
     * Do not add new instance variables or modify existing ones.
     */
    private T[] backingArray;
    private int size;

    /**
     * Constructs a new ArrayStack with a backing array with capacity
     * INITIAL_CAPACITY.
     */
    public ArrayStack() {
        backingArray = (T[]) (new Object[INITIAL_CAPACITY]);
        size = 0;
    }

    /**
     * Adds the data to the top of the stack.
     *
     * If sufficient space is not available in the backing array, resize it to
     * double the current length.
     *
     * Must be amortized O(1).
     *
     * @param data the data to add to the top of the stack
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void push(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Invalid data input(null).");
        }
        if (size == backingArray.length) {
            T[] newBackingArray = (T[]) (new Object[backingArray.length * 2]);
            for (int i = 0; i < backingArray.length; i++) {
                newBackingArray[i] = backingArray[i];
            }
            backingArray = newBackingArray;
        }
        backingArray[size] = data;
        size++;
    }

    /**
     * Removes and returns the data from the top of the stack.
     *
     * Do not shrink the backing array.
     *
     * Replace any spots that you pop from with null.
     *
     * Must be O(1).
     *
     * @return the data formerly located at the top of the stack
     * @throws java.util.NoSuchElementException if the stack is empty
     */
    public T pop() {
        if (size == 0) {
            throw new NoSuchElementException("Can't pop data from empty stack.");
        }
        T target = peek();
        backingArray[size - 1] = null;
        size--;
        return target;
    }

    /**
     * Returns the data from the top of the stack without removing it.
     *
     * Must be O(1).
     *
     * @return the data from the top of the stack
     * @throws NoSuchElementException if the stack is empty
     */
    public T peek() {
        if (size == 0) {
            throw new NoSuchElementException("Can't peek data from empty stack.");
        }
        return backingArray[size - 1];
    }

    /**
     * Returns the backing array of the stack.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the backing array of the stack
     */
    public T[] getBackingArray() {
        // DO NOT MODIFY THIS METHOD!
        return backingArray;
    }

    /**
     * Returns the size of the stack.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the size of the stack
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }
}
