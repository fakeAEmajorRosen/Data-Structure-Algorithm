import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * This is my first time writing JUnits so lets hope they work
 *
 * @author Alon Baruch
 * @version 1.0
 */

public class SinglyLinkedListTestAlonBaruch {

    // create testing environment
    private static final int TIMEOUT = 200;
    private SinglyLinkedList<String> list;

    // creates an empty SinglyLinkedList
    @Before
    public void setUp() {
        list = new SinglyLinkedList<>();
    }

    // Test to see if SinglyLinkedList was made
    @Test(timeout = TIMEOUT)
    public void testInitialization() {
        assertEquals(0, list.size());
        assertNull(list.getHead());
        assertNull(list.getTail());
    }

    /**
     *  Tests addAtIndex
     *
     *  test cases:
     *      addAtIndex on an empty list
     *      addAtIndex middle of list
     *      addAtIndex(head)
     *      addAtIndex(tail)
     *      addAtIndex(-1)
     *      addAtIndex(greater than size)
     *      addAtIndex with null data
     *
     */

    @Test(timeout = TIMEOUT)
    public void testAddAtIndexFrontOfEmptyList() {
        // adding at front of empty list
        list.addAtIndex(0, "head"); // head

        // checks that size is 1
        assertEquals(1, list.size());

        // checks that head is not null and is equal to data we passed in
        SinglyLinkedListNode<String> cur = list.getHead();
        assertNotNull(cur);
        assertEquals("head", cur.getData());

        cur = cur.getNext();
        assertNull(cur);
    }

    @Test(timeout = TIMEOUT)
    public void testAddAtIndexMiddle() {
        // adding at front of empty list
        list.addAtIndex(0, "3"); // 3
        list.addAtIndex(0, "1"); // 1 3
        list.addAtIndex(1, "2"); // 1 2 3

        // checks that size is 3
        assertEquals(3, list.size());

        // checks that head is not null and is equal to data we passed in
        SinglyLinkedListNode<String> cur = list.getHead();
        assertNotNull(cur);
        assertEquals("1", cur.getData());
        cur = cur.getNext();

        assertNotNull(cur);
        assertEquals("2", cur.getData());
        cur = cur.getNext();

        assertNotNull(cur);
        assertEquals("3", cur.getData());
        cur = cur.getNext();

        assertNull(cur);
    }

    @Test(timeout = TIMEOUT)
    public void testAddAtIndexHead() {
        // adding several elements to front of list
        list.addAtIndex(0, "4"); // 4
        list.addAtIndex(0, "3"); // 3 4
        list.addAtIndex(0, "2"); // 2 3 4
        list.addAtIndex(0, "1"); // 1 2 3 4

        // checks that size is 4
        assertEquals(4, list.size());

        // checks that head is not null and is equal to 1
        SinglyLinkedListNode<String> cur = list.getHead();
        assertNotNull(cur);
        assertEquals("1", cur.getData());
        cur = cur.getNext();

        // checks that second element is not null and is equal to 2
        assertNotNull(cur);
        assertEquals("2", cur.getData());
        cur = cur.getNext();

        // checks that third element is not null and is equal to 3
        assertNotNull(cur);
        assertEquals("3", cur.getData());
        cur = cur.getNext();

        // checks that fourth element is not null and is equal to 4
        assertNotNull(cur);
        assertEquals("4", cur.getData());

        cur = cur.getNext();
        assertNull(cur);
    }

    @Test(timeout = TIMEOUT)
    public void testAddAtIndexTail() {
        // adding several elements to back of list
        list.addAtIndex(0, "1"); // 1
        list.addAtIndex(1, "2"); // 1 2
        list.addAtIndex(2, "3"); // 1 2 3
        list.addAtIndex(3, "4"); // 1 2 3 4

        // checks that size is 4
        assertEquals(4, list.size());

        // checks that head is not null and is equal to 1
        SinglyLinkedListNode<String> cur = list.getHead();
        assertNotNull(cur);
        assertEquals("1", cur.getData());
        cur = cur.getNext();

        // checks that second element is not null and is equal to 2
        assertNotNull(cur);
        assertEquals("2", cur.getData());
        cur = cur.getNext();

        // checks that third element is not null and is equal to 3
        assertNotNull(cur);
        assertEquals("3", cur.getData());
        cur = cur.getNext();

        // checks that fourth element is not null and is equal to 4
        assertNotNull(cur);
        assertEquals("4", cur.getData());

        cur = cur.getNext();
        assertNull(cur);
    }

    // this test expects an IndexOutOfBoundsException to be thrown
    @Test(expected = IndexOutOfBoundsException.class)
    public void testAddAtIndexNegative() {
        //adding at index -1
        list.addAtIndex(-1, "negative");
    }

    // this test also expects an IndexOutOfBoundsException to be thrown
    @Test(expected = IndexOutOfBoundsException.class)
    public void testAddAtIndexOutOfBounds() {
        //adding at index larger than size
        list.addAtIndex(1, "Too Large");
    }

    // this test expects an IllegalArgumentException to be thrown
    @Test(expected = IllegalArgumentException.class)
    public void testAddAtIndexNullData() {
        //adding null data
        list.addAtIndex(0, null);
    }

    /**
     * Test addToFront
     *
     * test cases:
     *      addToFront on an empty list
     *      addToFront with null data
     *      addToFront several times
     *
     */

    @Test(timeout = TIMEOUT)
    public void testAddToFrontOfEmptyList() {
        // adding at front of empty list
        list.addToFront("head"); // head

        // checks that size is 1
        assertEquals(1, list.size());

        // checks that head is not null and is equal to data we passed in
        SinglyLinkedListNode<String> cur = list.getHead();
        assertNotNull(cur);
        assertEquals("head", cur.getData());

        cur = cur.getNext();
        assertNull(cur);
    }

    // this test expects an IllegalArgumentException to be thrown
    @Test(expected = IllegalArgumentException.class)
    public void testAddToFrontNullData() {
        //adding null data
        list.addToFront(null);
    }

    @Test(timeout = TIMEOUT)
    public void testAddToFrontSeveralTimes() {
        // adding at front of empty list
        list.addToFront("4"); // 4
        list.addToFront("3"); // 3 4
        list.addToFront("2"); // 2 3 4
        list.addToFront("1"); // 1 2 3 4

        // checks that size is 4
        assertEquals(4, list.size());

        // checks that head is not null and is equal to data we passed in
        SinglyLinkedListNode<String> cur = list.getHead();
        assertNotNull(cur);
        assertEquals("1", cur.getData());
        cur = cur.getNext();

        assertNotNull(cur);
        assertEquals("2", cur.getData());
        cur = cur.getNext();

        assertNotNull(cur);
        assertEquals("3", cur.getData());
        cur = cur.getNext();

        assertNotNull(cur);
        assertEquals("4", cur.getData());
        cur = cur.getNext();

        assertNull(cur);
    }

    /**
     * Test addToBack
     *
     * test cases:
     *      addToBack on an empty list
     *      addToBack with null data
     *      addToBack several times
     */

    @Test(timeout = TIMEOUT)
    public void testAddToBackOfEmptyList() {
        // adding at front of empty list
        list.addToBack("head"); // head

        // checks that size is 1
        assertEquals(1, list.size());

        // checks that head is not null and is equal to data we passed in
        SinglyLinkedListNode<String> cur = list.getHead();
        assertNotNull(cur);
        assertEquals("head", cur.getData());

        cur = cur.getNext();
        assertNull(cur);
    }

    // this test expects an IllegalArgumentException to be thrown
    @Test(expected = IllegalArgumentException.class)
    public void testAddToBackNullDat() {
        //adding null data
        list.addToBack(null);
    }

    @Test(timeout = TIMEOUT)
    public void testAddToBackSeveralTimes() {
        // adding at front of empty list
        list.addToBack("1"); // 1
        list.addToBack("2"); // 1 2
        list.addToBack("3"); // 1 2 3
        list.addToBack("4"); // 1 2 3 4

        // checks that size is 4
        assertEquals(4, list.size());

        // checks that head is not null and is equal to data we passed in
        SinglyLinkedListNode<String> cur = list.getHead();
        assertNotNull(cur);
        assertEquals("1", cur.getData());
        cur = cur.getNext();

        assertNotNull(cur);
        assertEquals("2", cur.getData());
        cur = cur.getNext();

        assertNotNull(cur);
        assertEquals("3", cur.getData());
        cur = cur.getNext();

        assertNotNull(cur);
        assertEquals("4", cur.getData());
        cur = cur.getNext();

        assertNull(cur);
    }

    /**
     * Test removeAtIndex
     *
     * test cases:
     *      removeAtIndex on an empty list
     *      removeAtIndex middle of a list
     *      removeAtIndex(head)
     *      removeAtIndex(tail)
     *      removeAtIndex(-1)
     *      removeAtIndex(greater than size)
     */

    // this test expects an IndexOutOfBoundsException
    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveAtIndexEmptyList() {
        list.removeAtIndex(0);
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveAtIndexMiddleOfList() {
        //Creates linked list
        list.addAtIndex(0, "1"); // 1
        list.addAtIndex(1, "2"); // 1 2
        list.addAtIndex(2, "3"); // 1 2 3
        list.addAtIndex(3, "4"); // 1 2 3 4

        // removes element at index 2
        list.removeAtIndex(2); // 1 2 4

        // checks the size is 3
        assertEquals(3, list.size());

        // checks that head is not null and is equal to data we passed in
        SinglyLinkedListNode<String> cur = list.getHead();
        assertNotNull(cur);
        assertEquals("1", cur.getData());
        cur = cur.getNext();

        assertNotNull(cur);
        assertEquals("2", cur.getData());
        cur = cur.getNext();

        assertNotNull(cur);
        assertEquals("4", cur.getData());
        cur = cur.getNext();

        assertNull(cur);
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveAtIndexHead() {
        //Creates linked list
        list.addAtIndex(0, "1"); // 1
        list.addAtIndex(1, "2"); // 1 2
        list.addAtIndex(2, "3"); // 1 2 3
        list.addAtIndex(3, "4"); // 1 2 3 4

        // removes element at index 0
        list.removeAtIndex(0); // 2 3 4

        // checks the size is 3
        assertEquals(3, list.size());

        // checks that head is not null and is equal to data we passed in
        SinglyLinkedListNode<String> cur = list.getHead();
        assertNotNull(cur);
        assertEquals("2", cur.getData());
        cur = cur.getNext();

        assertNotNull(cur);
        assertEquals("3", cur.getData());
        cur = cur.getNext();

        assertNotNull(cur);
        assertEquals("4", cur.getData());
        cur = cur.getNext();

        assertNull(cur);
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveAtIndexTail() {
        //Creates linked list
        list.addAtIndex(0, "1"); // 1
        list.addAtIndex(1, "2"); // 1 2
        list.addAtIndex(2, "3"); // 1 2 3
        list.addAtIndex(3, "4"); // 1 2 3 4

        // removes element at index 0
        list.removeAtIndex(3); // 1 2 3

        // checks the size is 3
        assertEquals(3, list.size());

        // checks that head is not null and is equal to data we passed in
        SinglyLinkedListNode<String> cur = list.getHead();
        assertNotNull(cur);
        assertEquals("1", cur.getData());
        cur = cur.getNext();

        assertNotNull(cur);
        assertEquals("2", cur.getData());
        cur = cur.getNext();

        assertNotNull(cur);
        assertEquals("3", cur.getData());
        cur = cur.getNext();

        assertNull(cur);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveAtIndexNegative() {
        list.removeAtIndex(-1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveAtIndexOutOfBounds() {
        list.removeAtIndex(1);
    }

    /**
     * Test removeFromFront
     *
     * test cases:
     *      removeFromFront on an empty list
     *      removeFromFront several times
     */

    @Test(expected = NoSuchElementException.class)
    public void testRemoveFromFrontEmptyList() {
        list.removeFromFront();
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveFromFrontSeveralTimes() {
        //Creates linked list
        list.addAtIndex(0, "1"); // 1
        list.addAtIndex(1, "2"); // 1 2
        list.addAtIndex(2, "3"); // 1 2 3
        list.addAtIndex(3, "4"); // 1 2 3 4

        //Removes from front 2 times
        list.removeFromFront(); // 2 3 4
        list.removeFromFront(); // 3 4

        // checks the size is 2
        assertEquals(2, list.size());

        // checks that head is not null and is equal to data we passed in
        SinglyLinkedListNode<String> cur = list.getHead();
        assertNotNull(cur);
        assertEquals("3", cur.getData());
        cur = cur.getNext();

        assertNotNull(cur);
        assertEquals("4", cur.getData());
        cur = cur.getNext();

        assertNull(cur);
    }

    /**
     * Test removeFromBack
     *
     * test cases:
     *      removeFromBack on an empty list
     *      removeFromBack several times
     *
     */

    @Test(expected = NoSuchElementException.class)
    public void testRemoveFromBackEmptyList() {
        list.removeFromFront();
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveFromBackSeveralTimes() {
        //Creates linked list
        list.addAtIndex(0, "1"); // 1
        list.addAtIndex(1, "2"); // 1 2
        list.addAtIndex(2, "3"); // 1 2 3
        list.addAtIndex(3, "4"); // 1 2 3 4

        //Removes from front 2 times
        list.removeFromBack(); // 1 2 3
        list.removeFromBack(); // 1 2

        // checks the size is 2
        assertEquals(2, list.size());

        // checks that head is not null and is equal to data we passed in
        SinglyLinkedListNode<String> cur = list.getHead();
        assertNotNull(cur);
        assertEquals("1", cur.getData());
        cur = cur.getNext();

        assertNotNull(cur);
        assertEquals("2", cur.getData());
        cur = cur.getNext();

        assertNull(cur);
    }

    /**
     * Test get
     *
     * test cases:
     *      get on empty list
     *      get(-1)
     *      get(size)
     *      get(head)
     *      get(tail)
     *      get(middle)
     *
     */

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetEmptyList() {
        list.get(0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetNegative() {
        list.get(-1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetOutOfBounds() {
        //Creates linked list
        list.addAtIndex(0, "1"); // 1
        list.addAtIndex(1, "2"); // 1 2
        list.addAtIndex(2, "3"); // 1 2 3
        list.addAtIndex(3, "4"); // 1 2 3 4

        // trys to get index 4
        list.get(list.size());
    }

    @Test(timeout = TIMEOUT)
    public void testGetHead() {
        //Creates linked list
        list.addAtIndex(0, "1"); // 1
        list.addAtIndex(1, "2"); // 1 2
        list.addAtIndex(2, "3"); // 1 2 3
        list.addAtIndex(3, "4"); // 1 2 3 4

        assertEquals("1", list.get(0));
    }

    @Test(timeout = TIMEOUT)
    public void testGetTail() {
        //Creates linked list
        list.addAtIndex(0, "1"); // 1
        list.addAtIndex(1, "2"); // 1 2
        list.addAtIndex(2, "3"); // 1 2 3
        list.addAtIndex(3, "4"); // 1 2 3 4

        assertEquals("4", list.get(3));
    }

    @Test(timeout = TIMEOUT)
    public void testGetMiddle() {
        //Creates linked list
        list.addAtIndex(0, "1"); // 1
        list.addAtIndex(1, "2"); // 1 2
        list.addAtIndex(2, "3"); // 1 2 3
        list.addAtIndex(3, "4"); // 1 2 3 4

        assertEquals("3", list.get(2));
    }


    /**
     * Test isEmpty
     *
     * test cases:
     *      isEmpty on empty list
     *      isEmpty on non-empty list
     */

    @Test(timeout = TIMEOUT)
    public void testIsEmptyOnEmptyList() {
        assertTrue(list.isEmpty());
    }

    @Test(timeout = TIMEOUT)
    public void testIsEmptyNonEmptyList() {
        //Creates linked list
        list.addAtIndex(0, "1"); // 1
        list.addAtIndex(1, "2"); // 1 2
        list.addAtIndex(2, "3"); // 1 2 3
        list.addAtIndex(3, "4"); // 1 2 3 4

        assertFalse(list.isEmpty());
    }

    /**
     * Test clear
     *
     * test cases:
     *      clear an empty list
     *      clear a non-empty list
     */

    @Test(timeout = TIMEOUT)
    public void testClearEmpty() {
        list.clear();

        assertEquals(0, list.size());
        assertNull(list.getHead());
    }

    @Test(timeout = TIMEOUT)
    public void testClearNonEmpty() {
        list.addAtIndex(0, "1");   // 1
        list.addAtIndex(1, "2");   // 1 2
        list.addAtIndex(2, "3");   // 1 2 3
        list.addAtIndex(3, "4");   // 1 2 3 4
        list.addAtIndex(4, "5");   // 1 2 3 4 5

        list.clear();

        assertEquals(0, list.size());
        assertNull(list.getHead());
    }

    /**
     * Test removeLastOccurrence
     *
     * test cases:
     *      removeLastOccurrence on empty list
     *      removeLastOccurrence head
     *      removeLastOccurrence tail
     *      removeLastOccurence middle
     *      removeLastOccurrence no occurrences
     *      removeLastOccurrence all duplicates
     */

    @Test(expected = NoSuchElementException.class)
    public void testRemoveLastOccurrenceEmptyList() {
        list.removeLastOccurrence("1");
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveLastOccurenceHead() {
        list.addAtIndex(0, "1");   // 1
        list.addAtIndex(1, "2");   // 1 2
        list.addAtIndex(2, "3");   // 1 2 3
        list.addAtIndex(3, "4");   // 1 2 3 4
        list.addAtIndex(4, "5");   // 1 2 3 4 5

        assertEquals("1", list.removeLastOccurrence("1"));
        assertEquals(4, list.size());

        // checks that head is not null and is equal to data we passed in
        SinglyLinkedListNode<String> cur = list.getHead();
        assertNotNull(cur);
        assertEquals("2", cur.getData());
        cur = cur.getNext();

        assertNotNull(cur);
        assertEquals("3", cur.getData());
        cur = cur.getNext();

        assertNotNull(cur);
        assertEquals("4", cur.getData());
        cur = cur.getNext();

        assertNotNull(cur);
        assertEquals("5", cur.getData());
        cur = cur.getNext();

        assertNull(cur);
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveLastOccurenceTail() {
        list.addAtIndex(0, "1");   // 1
        list.addAtIndex(1, "2");   // 1 2
        list.addAtIndex(2, "3");   // 1 2 3
        list.addAtIndex(3, "4");   // 1 2 3 4
        list.addAtIndex(4, "5");   // 1 2 3 4 5

        assertEquals("5", list.removeLastOccurrence("5"));
        assertEquals(4, list.size());

        // checks that head is not null and is equal to data we passed in
        SinglyLinkedListNode<String> cur = list.getHead();
        assertNotNull(cur);
        assertEquals("1", cur.getData());
        cur = cur.getNext();

        assertNotNull(cur);
        assertEquals("2", cur.getData());
        cur = cur.getNext();

        assertNotNull(cur);
        assertEquals("3", cur.getData());
        cur = cur.getNext();

        assertNotNull(cur);
        assertEquals("4", cur.getData());
        cur = cur.getNext();

        assertNull(cur);
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveLastOccurenceMiddle() {
        list.addAtIndex(0, "1");   // 1
        list.addAtIndex(1, "3");   // 1 3
        list.addAtIndex(2, "3");   // 1 3 3
        list.addAtIndex(3, "4");   // 1 3 3 4
        list.addAtIndex(4, "5");   // 1 3 3 4 5

        assertEquals("3", list.removeLastOccurrence("3"));
        assertEquals(4, list.size());

        // checks that head is not null and is equal to data we passed in
        SinglyLinkedListNode<String> cur = list.getHead();
        assertNotNull(cur);
        assertEquals("1", cur.getData());
        cur = cur.getNext();

        assertNotNull(cur);
        assertEquals("3", cur.getData());
        cur = cur.getNext();

        assertNotNull(cur);
        assertEquals("4", cur.getData());
        cur = cur.getNext();

        assertNotNull(cur);
        assertEquals("5", cur.getData());
        cur = cur.getNext();

        assertNull(cur);
    }

    @Test(expected = NoSuchElementException.class)
    public void testRemoveLastOccurenceNoSuchElement() {
        list.addAtIndex(0, "1");   // 1
        list.addAtIndex(1, "2");   // 1 2
        list.addAtIndex(2, "3");   // 1 2 3
        list.addAtIndex(3, "4");   // 1 2 3 4
        list.addAtIndex(4, "5");   // 1 2 3 4 5

        list.removeLastOccurrence("6");
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveLastOccurenceAllDuplicates() {
        list.addAtIndex(0, "1");   // 1
        list.addAtIndex(1, "1");   // 1 1
        list.addAtIndex(2, "1");   // 1 1 1
        list.addAtIndex(3, "1");   // 1 1 1 1
        list.addAtIndex(4, "1");   // 1 1 1 1 1

        assertEquals("1", list.removeLastOccurrence("1"));
        assertEquals(4, list.size());

        // checks that head is not null and is equal to data we passed in
        SinglyLinkedListNode<String> cur = list.getHead();
        assertNotNull(cur);
        assertEquals("1", cur.getData());
        cur = cur.getNext();

        assertNotNull(cur);
        assertEquals("1", cur.getData());
        cur = cur.getNext();

        assertNotNull(cur);
        assertEquals("1", cur.getData());
        cur = cur.getNext();

        assertNotNull(cur);
        assertEquals("1", cur.getData());
        cur = cur.getNext();

        assertNull(cur);
    }

    /**
     * Test toArray
     *
     * test cases:
     *      toArray regular list
     *      (couldn't figure out how to run on empty list without getting errors ¯\_(ツ)_/¯)
     */

    @Test(timeout = TIMEOUT)
    public void testToArray() {
        list.addAtIndex(0, "1");   // 1
        list.addAtIndex(1, "2");   // 1 2
        list.addAtIndex(2, "3");   // 1 2 3
        list.addAtIndex(3, "4");   // 1 2 3 4
        list.addAtIndex(4, "5");   // 1 2 3 4 5

        String[] expected = new String[5];
        expected[0] = "1";
        expected[1] = "2";
        expected[2] = "3";
        expected[3] = "4";
        expected[4] = "5";

        assertArrayEquals(expected, list.toArray());
    }
}
