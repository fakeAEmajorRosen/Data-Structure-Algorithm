// Iterator:
java.util.Iterator
boolean hasNext()
T next()
remove() //Not all data structures are required to support remove()

// The way that use may use the iterator:
Iterator<Integer> LLIterator = ll.iterator();
while(LLIterator.hasNext()) {
	Integer data = LLIterator.next();
	System.out.print(data +" ");
}


// Iterable:
java.lang.Iterable
Iterator iterator()

// The way that use may use the iterable:
for (T datam : list) {
	System.out.print(datam);
}

// Implement Iterator for LL
public class LinkedList<T> implements Iterable<T> {
	@Override
	public Iterator<T> iterator() {
		return new LLIterator<T>(head);
	}
	private static class LLIterator<T> implements Iterator<T> {
		private Node<T> currNode;

		public LLIterator(Node<T> startingNode) {
			currNode = startingNode;
		}

		@Override 
		public boolean hasNext() {
			return currNode != null;
		}

		@Override 
		public T nextt() {
			if (!hasNext) {
				throw new Exception;
			}
			T currData = currNode.data;
			currNode = currNode.next;
			return currData;
		}
	}
}

// LinkedList
// 1. add to front
public void addToFront(T data) {
	// Create a new node
	// set the new node point to the head
	// Set the head to the new node
	head = new Node(data, head);
}

// 2. toString: Iterate through the list
public String toString() {
	// Creat a traverse call "current"
	Node current = head;
	String ans = "";
	// Iterate throug the list till current == null
	while (current != null) {
		answer += current + " ";
		current = current.next;
	}
	return answer;
}

// 3. Index of
wrapper {
	return indexof(data, head, 0);
}
int indexof(T data, Node current, index) {
	if (curr == null) {
		return -1;
	}
	if (curr.data.equals(data)) {
		return index
	} else {
		return indexof(data, current.next, index + 1);
	}
}

// Quiz: sumBetween
int sumBetween(int start, int end) {
	int sum = 0;
	Node current = head;
	for (int i = 0; i < start + 1 ; i++) {
		current = current.next;
	}
	for (int i = start + 1; i < end; i++) {
		sum += current.data;
		current = current.next;
	}
}

// Tracing example: Recursive call on the LL
// LL: 25>28>6>58>18>37>||
// Output: 25 28 END 18 6 
// return: 34
public int recur(Node curr) {
	if (curr == null) {
		System.out.println("End");
		return 0;
	} else if (curr.data > 20) {
		System.out.println(curr.data);
		curr = ccurr.next;
		return 5 + recur(curr);
	} else {
		int x = curr.data + recur(curr.next.next);
		System.out.println(curr.data);
		return x;
	}
}

// Recursive BST: get() for BST
// base case 1: curr == null
// base case 2: curr.data = target
T get(Node target, Node curr) {
	if (curr == null) {
		throw new Exception;
	} else if {
		return curr.data;
	} else {
		if (target > curr) {
			curr.right = get()
		}
	}
}

// replace data:
Node curr = head; 
int idx = 0;
while (idx < index) {
	curr = curr.next;
	idx++;
} 
curr.data = data;

// DLL removeFromBack: O(1)
tail  = tail.previous;
tail.setNext (null);


//thCounnt
if (data == null) {
	throw new Exception;
}
Node curr = head;
int count = 0;
for (int i = 0; i < size; i++) {
	if (curr.data.compareTo(data) > 0) {
		count++;
	}
	curr = curr.next;
}
return count;

// toArray

Comparable[] arr = new Comparable[size];
Node curr = head;
for (int i = 0; i < size; i++) {
	arr[i] = curr.data;
	curr = curr.next;
}
return arr;
