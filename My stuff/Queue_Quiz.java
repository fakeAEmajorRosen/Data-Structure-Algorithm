// Queue Quiz
public class Queue<T>  {
    private int size;      // number of elements on queue
    private Node head;     // beginning of queue
    private Node tail;     // end of queue

    public Queue() {
    	size = 0; 
    // No need to initialize head and tail since they are null by default
    // size would also be 0 by default, so we could leave this constructor blank if we wanted
	}

	public boolean isEmpty() {
    	return size == 0;
	}

	public void enqueue(T data) {
	    if (size == 0) {
	        head = new Node<>(data, null);
	        tail = head;
	    } else {
	        tail.next = new Node<>(data, null);
	        tail = tail.next;
	    }
    	size++;
	}

    // helper linked list class
    private class Node {
        private T data;      // the item in the node
        private Node next;   // reference to next item

        private Node(T data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

}

public class Queue<T>  {
    private Stack<T> s1;
    private Stack<T> s2;

    /**
     * Initializes an empty Queue
     */

    public Queue<T>() {
        s1 = new Stack<>();
        s2 = new Stack<>();
    }

    /**
     * Enqueues the data onto the Queue, assuming the data is not null.
     *
     * This implementation makes the dequeue operation expensive and the enqueue operation cheap.
     * You can also make the enqueue operation expensive and the dequeue operation cheap instead.
     *
     * @param data the data to be enqueued
     */
    public void enqueue(T data) {
        s1.push(data);
    }

    /**
     * Dequeues some data from the Queue
     *
     * @return the dequeued data
     * @throws java.util.NoSuchElementException if the Queue is empty
     */
    public T dequeue() {
        if (s1.size() == 0 && s2.size() == 0) {
            throw new java.util.NoSuchElementException("Queue is empty.");
        } else {
            if (s2.size() == 0) {         
                while (s1.size() != 0) {
                    s2.push(s1.pop());
                }
            }
            return s2.pop();
        }
    }

    /**
     * Returns the size of the Queue
     *
     * @return the number of items in the Queue
     */
    public int size() {
        return s1.size() + s2.size();
    }
}