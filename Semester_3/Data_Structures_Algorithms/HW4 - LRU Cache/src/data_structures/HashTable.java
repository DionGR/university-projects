package data_structures;

/**
 * Hash Table Abstract Class
 *
 * A blueprint for our Hash Tables.
 * Utilizes a Hash Function to determine the index of the key,
 * as seen in Algorithms 4th Edition by Robert Sedgewick.
 *
 * @author P3200262
 * @info Made for the course of Data Structures @ AUEB 2021-2022 as a BONUS assignment
 **/

public abstract class HashTable<K, V> {
    protected final int INITIAL_CAPACITY = 101;
    protected int size;
    protected int capacity;
    
    
    public HashTable() {
        this.size = 0;
        this.capacity = INITIAL_CAPACITY;
    }
    
    public HashTable(int capacity) {
        this.size = 0;
        this.capacity = capacity;
    }
    
    /**
     * Returns the index of the key in the Hash Table.
     * Masks off the sign bit to ensure that the index is positive.
     */
    protected int hash(K key) {
        return (key.hashCode() & 0x7fffffff) % capacity;
    }
    
    public boolean contains(K key) {
        return get(key) != null;
    }
    
    public int size() {
        return size;
    }
    
    public abstract void put(K key, V value);
    
    public abstract V get(K key);
    
    public abstract void remove(K key);
}
