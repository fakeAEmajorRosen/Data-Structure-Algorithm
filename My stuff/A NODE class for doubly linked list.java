// A node class for doubly linked list

private class Node<T> {
    private T data;
    private Node<T> next;
    private Node<T> prev;

    public Node(T data, Node<T> next, Node<T> prev) {
       this.data = data;
       this.next = next;
       this.prev = prev;
    }

    public Node(T data) {
       this(data, null, null);
    }

    public Node<T> getNext() {
       return next;
    }

    public Node<T> getPrevious() {
       return prev;
    }

    public void setNext(Node<T> next) {
       this.next = next;
    }

    public void setPrevious(Node<T> prev) {
       this.prev = prev;
    }

    public T getData() {
       return data;
    }

    public void setData(T data) {
       this.data = data;
    } 
}