import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.ArrayList;

/**
 * Your implementation of a ExternalChainingHashMap.
 *
 * @author Ting-Ying Yu
 * @version 1.0
 * @userid tyu304
 * @GTID 903534212
 *
 * Collaborators: I work on this assignment alone.
 *
 * Resources: I only use the course materials.
 */
public class ExternalChainingHashMap<K, V> {

    /*
     * The initial capacity of the ExternalChainingHashMap when created with the
     * default constructor.
     *
     * DO NOT MODIFY THIS VARIABLE!
     */
    public static final int INITIAL_CAPACITY = 13;

    /*
     * The max load factor of the ExternalChainingHashMap.
     *
     * DO NOT MODIFY THIS VARIABLE!
     */
    public static final double MAX_LOAD_FACTOR = 0.67;

    /*
     * Do not add new instance variables or modify existing ones.
     */
    private ExternalChainingMapEntry<K, V>[] table;
    private int size;

    /**
     * Constructs a new ExternalChainingHashMap.
     *
     * The backing array should have an initial capacity of INITIAL_CAPACITY.
     *
     * Use constructor chaining.
     */
    public ExternalChainingHashMap() {
        this(INITIAL_CAPACITY);
    }

    /**
     * Constructs a new ExternalChainingHashMap.
     *
     * The backing array should have an initial capacity of initialCapacity.
     *
     * You may assume initialCapacity will always be positive.
     *
     * @param initialCapacity the initial capacity of the backing array
     */
    public ExternalChainingHashMap(int initialCapacity) {
        table = new ExternalChainingMapEntry[initialCapacity];
    }

    /**
     * Adds the given key-value pair to the map. If an entry in the map
     * already has this key, replace the entry's value with the new one
     * passed in.
     *
     * In the case of a collision, use external chaining as your resolution
     * strategy. Add new entries to the front of an existing chain, but don't
     * forget to check the entire chain for duplicate keys first.
     *
     * If you find a duplicate key, then replace the entry's value with the new
     * one passed in. When replacing the old value, replace it at that position
     * in the chain, not by creating a new entry and adding it to the front.
     *
     * Before actually adding any data to the HashMap, you should check to
     * see if the array would violate the max load factor if the data was
     * added. Resize if the load factor (LF) is greater than max LF (it is okay
     * if the load factor is equal to max LF). For example, let's say the
     * array is of length 5 and the current size is 3 (LF = 0.6). For this
     * example, assume that no elements are removed in between steps. If
     * another entry is attempted to be added, before doing anything else,
     * you should check whether (3 + 1) / 5 = 0.8 is larger than the max LF.
     * It is, so you would trigger a resize before you even attempt to add
     * the data or figure out if it's a duplicate. Be careful to consider the
     * differences between integer and double division when calculating load
     * factor.
     *
     * When regrowing, resize the length of the backing table to
     * (2 * old length) + 1. You must use the resizeBackingTable method to do so.
     *
     * Return null if the key was not already in the map. If it was in the map,
     * return the old value associated with it.
     *
     * @param key   the key to add
     * @param value the value to add
     * @return null if the key was not already in the map. If it was in the
     * map, return the old value associated with it
     * @throws java.lang.IllegalArgumentException if key or value is null
     */
    public V put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("The key cannot be null.");
        } else if (value == null) {
            throw new IllegalArgumentException("The value cannot be null.");
        }
        if (table.length == 0 || (((double) (size + 1)) / ((double) table.length)) > MAX_LOAD_FACTOR) {
            // check resize
            resizeBackingTable(table.length * 2 + 1);
        }
        ExternalChainingMapEntry<K, V> curr = getEntry(key);
        V out = null;
        // If there is a duplication
        if (curr != null) {
            // Replace the existed node with same key with the new value
            out = curr.getValue();
            curr.setValue(value);
            // Output the old value
            return out;

        }
        // A helper method that skipped resizing and duplications
        putH(key, value);
        return null;
    }

    /**
     * A helper method for put(), puts key-value pair in table
     * without resize and checking duplicates
     * @param key   the key to add
     * @param value the value to add
     * @throws java.lang.IllegalArgumentException if key or value is null
     */
    private void putH(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("The key cannot be null.");
        } else if (value == null) {
            throw new IllegalArgumentException("The value cannot be null.");
        }
        int index = Math.abs(key.hashCode() % table.length);
        // Add the new node to the front of the list (collision)
        table[index] = new ExternalChainingMapEntry<K, V>(key, value, table[index]);
        size++;
    }


    /**
     * Removes the entry with a matching key from the map.
     *
     * @param key the key to remove
     * @return the value associated with the key
     * @throws java.lang.IllegalArgumentException if key is null
     * @throws java.util.NoSuchElementException   if the key is not in the map
     */
    public V remove(K key) {
        if (key == null) {
            throw new IllegalArgumentException("The key cannot be null.");
        } else if (table.length == 0) {
            throw new NoSuchElementException("The key is not in the map.");
        }
        int index = Math.abs(key.hashCode() % table.length);
        V out;

        if (table[index] == null) {
            // if the first node is null
            throw new NoSuchElementException("The key is not in the map.");
        } else if (table[index].getKey().equals(key)) {
            // if the first node has the same key
            out = table[index].getValue();
            table[index] = table[index].getNext();
            size--;
            return out;
        }

        // The first node is not null and has a different key
        // Use getPrevEntry to check the rest of the list
        ExternalChainingMapEntry<K, V> node = getPrevEntry(key);
        if (node == null) {
            throw new NoSuchElementException("The key is not in the map");
        } else {
            // Singly Linked List remove
            out = node.getNext().getValue();
            node.setNext(node.getNext().getNext());
        }
        size--;
        return out;
    }

    /**
     * Gets the value associated with the given key.
     *
     * @param key the key to search for in the map
     * @return the value associated with the given key
     * @throws java.lang.IllegalArgumentException if key is null
     * @throws java.util.NoSuchElementException   if the key is not in the map
     */
    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException("The key input cannot be null.");
        }
        ExternalChainingMapEntry<K, V> curr = getEntry(key);
        if (curr == null) {
            throw new NoSuchElementException("Key is not in map.");
        }
        return curr.getValue();
    }

    /**
     * A helper method for get() that returns the node by the input key.
     *
     * @param key the key to search for in the map
     * @return the node associated with the given key
     * @throws java.lang.IllegalArgumentException if key is null
     */
    private ExternalChainingMapEntry<K, V> getEntry(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Cannot get null key from map.");
        } else if (size == 0) {
            return null;
        }
        int index = Math.abs(key.hashCode() % table.length);
        if (table[index] == null) {
            // if the index has nothing
            return null;
        } else if (table[index].getKey().equals(key)) {
            // if the first node of the list has the same key
            return table[index];
        }
        // check the rest of the list with getPrevEntry
        ExternalChainingMapEntry<K, V> previous = getPrevEntry(key);
        if (previous == null) {
            return null;
        }
        return previous.getNext();
    }

    /**
     * A helper method for getEntry() that returns the node before the target
     * by the input key.
     *
     * @param key the key to search for in the map
     * @return the node before the node associated with the given key
     * @throws java.lang.IllegalArgumentException if key is null
     */
    private ExternalChainingMapEntry<K, V> getPrevEntry(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Cannot get null key from map.");
        } else if (size == 0) {
            return null;
        }
        int index = Math.abs(key.hashCode() % table.length);
        ExternalChainingMapEntry<K, V> curr = table[index];
        if (curr == null) {
            return null;
        }
        while (curr.getNext() != null) {
            // Loop through the list
            if (curr.getNext().getKey().equals(key)) {
                // return the node before the one with the same key
                return curr;
            }
            curr = curr.getNext();
        }
        return null;
    }

    /**
     * Returns whether or not the key is in the map.
     *
     * @param key the key to search for in the map
     * @return true if the key is contained within the map, false
     * otherwise
     * @throws java.lang.IllegalArgumentException if key is null
     */
    public boolean containsKey(K key) {
        if (key == null) {
            throw new IllegalArgumentException("The input key cannot be null.");
        }
        return getEntry(key) != null;
    }

    /**
     * Returns a Set view of the keys contained in this map.
     *
     * Use java.util.HashSet.
     *
     * @return the set of keys in this map
     */
    public Set<K> keySet() {
        Set<K> set = new HashSet<K>(size);
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) {
                ExternalChainingMapEntry<K, V> curr = table[i];
                while (curr != null) {
                    set.add(curr.getKey());
                    curr = curr.getNext();
                }
            }
        }
        return set;
    }

    /**
     * Returns a List view of the values contained in this map.
     *
     * Use java.util.ArrayList or java.util.LinkedList.
     *
     * You should iterate over the table in order of increasing index and add
     * entries to the List in the order in which they are traversed.
     *
     * @return list of values in this map
     */
    public List<V> values() {
        List<V> list = new ArrayList<V>();
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) {
                ExternalChainingMapEntry<K, V> curr = table[i];
                while (curr != null) {
                    list.add(curr.getValue());
                    curr = curr.getNext();
                }
            }
        }
        return list;
    }

    /**
     * Resize the backing table to length.
     *
     * Disregard the load factor for this method. So, if the passed in length is
     * smaller than the current capacity, and this new length causes the table's
     * load factor to exceed MAX_LOAD_FACTOR, you should still resize the table
     * to the specified length and leave it at that capacity.
     *
     * You should iterate over the old table in order of increasing index and
     * add entries to the new table in the order in which they are traversed.
     *
     * Since resizing the backing table is working with the non-duplicate
     * data already in the table, you shouldn't explicitly check for
     * duplicates.
     *
     * Hint: You cannot just simply copy the entries over to the new array.
     *
     * @param length new length of the backing table
     * @throws java.lang.IllegalArgumentException if length is less than the
     *                                            number of items in the hash
     *                                            map
     */
    public void resizeBackingTable(int length) {
        if (length < 0 || length < size) {
            throw new IllegalArgumentException("New table capacity need to be larger than size.");
        }
        ExternalChainingMapEntry<K, V>[] newArray = new ExternalChainingMapEntry[length];
        int count = 0;
        for (int i = 0; i < table.length; i++) {
            if (count == size) {
                // return when everything is already in the new array
                break;
            }
            if (table[i] != null) {
                ExternalChainingMapEntry<K, V> curr = table[i];
                while (curr != null) {
                    int index = Math.abs(curr.getKey().hashCode() % newArray.length);
                    newArray[index] = new ExternalChainingMapEntry<K, V>(curr.getKey(), curr.getValue(), newArray[index]);
                    curr = curr.getNext();
                    count++;
                }
            }
        }
        table = newArray;
    }

    /**
     * Clears the map.
     *
     * Resets the table to a new array of the initial capacity and resets the
     * size.
     */
    public void clear() {
        table = new ExternalChainingMapEntry[INITIAL_CAPACITY];
        size = 0;
    }

    /**
     * Returns the table of the map.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the table of the map
     */
    public ExternalChainingMapEntry<K, V>[] getTable() {
        // DO NOT MODIFY THIS METHOD!
        return table;
    }

    /**
     * Returns the size of the map.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the size of the map
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }
}
