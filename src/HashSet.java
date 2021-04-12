import java.util.*;


/**
 * A hash table-based implementation of the Set interface.
 *
 * @author Raeef Bechara
 * @version 2021-02-11
 */
public class HashSet<T> implements Set<T> {
    private List[] table;
    private int amountOfElements = 0;
    /**
     * Creates a hash table with the given capacity (amount of buckets).
     *
     * @throws IllegalArgumentException if capacity <= 0.
     */
    public HashSet(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException(
                    "capacity must be a positive, non-zero value! Provided: " + capacity);
        }
        // We want to do the following:
        //
        // table = new LinkedList[capacity];
        //
        // However, that won't compile ("generic array creation")
        // since Java generics and arrays don't get along very well.
        // Instead we need to do the following:
        //
        // table = new LinkedList[capacity];
        //
        // The above will compile, but with a warning. The proper
        // approach is to document why the warning can be safely
        // ignored and then suppress the warning. Thus:
        /*
         * This array will contain only LinkedList
         * instances, all created in the add method. This
         * is sufficient to ensure type safety.
         */
        @SuppressWarnings("unchecked") // for this declaration only
        List[] t = new LinkedList[capacity];
        table = t;
    }
    /**
     * Adds the given element to the set.
     *
     * Complexity: O(1) expected time.
     *
     * @param elem An element to add to the set.
     * @return true if the set did not contain the element, otherwise false.
     */
    @Override
    public boolean add(T elem) {
        ++amountOfElements;
        int index = elem.hashCode() % table.length;
        boolean t_contains = contains(elem);
        if(table[index] == null) {
            table[index] = new LinkedList();
        }
        @SuppressWarnings("unchecked") // for this declaration only
        List<T> list = table[index];
        list.add(elem);
        return !t_contains;
    }


    /**
     * Removes the given element from the dictionary, if it is present.
     *
     * Complexity: O(1) expected time.
     *
     * @param elem An element to remove from the set.
     * @return true if the set contained the element, false otherwise.
     */
    @Override
    public boolean remove(Object elem) {
        --amountOfElements;
        int index = elem.hashCode() % table.length;
        if(table[index] == null) {
            return false;
        }
        boolean t_contains = contains(elem);
        table[index].remove(elem);
        return t_contains;
    }
    /**
     * Check if an element is in the Set.
     *
     * Complexity: O(1) expected time.
     *
     * @param elem An element to look for.
     * @return true if the element is in the set, false otherwise.
     */
    @Override
    public boolean contains(Object elem) {
        int index = elem.hashCode() % table.length;
        if(table[index] == null) {
            return false;
        }
        return table[index].contains(elem);
    }
    /**
     * Returns the number of elements in this set.
     *
     * Complexity: O(1) expected time.
     *
     * @return The amount of elements in this set.
     */
    @Override
    public int size() {
        return amountOfElements;
    }
}
