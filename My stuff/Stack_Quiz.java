public interface StackADT<T> {

    /**
     * Pushes an item onto the top of this stack.
     * Throw java.lang.RuntimeException if the array is full.
     * Do not increase the physical size of the array.
     */
     public void push(T item);

    /**
     * Removes the item at the top of this stack and returns that object.
     * Throws java.util.EmptyStackException if the stack is empty.
     */
     public T pop();

    /**
     * Tests if this stack is empty.
     */
     public boolean isEmpty();
}

import java.util.EmptyStackException;
public class Stack implements StackADT<T> {
    private T[] backingArray;
    private int size;

    public Stack() {
        this.backingArray = (T[]) new Object[100];
    }

    public void push(T item) {
        if (size == backingArray.length) {
            throw java.lang.RuntimeException("Stack is full");
        }

        backingArray[size] = data;
        size++;
    }

    public T pop() {
        if (size == 0) {
            throw java.util.EmptyStackException("Stack is empty");
        }

        T item = backingArray[size - 1];
        backingArray[size - 1] = null;
        size--;
        return item;
    }

    public boolean isEmpty() {
        return size == 0;
    }

}