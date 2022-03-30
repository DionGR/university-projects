package data_structures;

/**
 * Hash Table - Linear Probing
 * A Linear Probing Fixed-Size Hash Table as shown
 * in Algorithms 4th Edition by Robert Sedgewick and Kevin Wayne.
 *
 *
 * @author P3200262
 * @info Made for the course of Data Structures @ AUEB 2021-2022 as a BONUS assignment
 **/

public class FixedHashTableLP<K, V> extends HashTable<K, V> {
    private K[] keys;
    private V[] values;
    
    public FixedHashTableLP() {
        super();
    }
    
    @SuppressWarnings("unchecked")
    public FixedHashTableLP(int capacity) {
        super(capacity);
        this.keys = (K[]) new Object[capacity];
        this.values = (V[]) new Object[capacity];
    }

    public void put(K key, V value) {
        if (key == null) throw new IllegalArgumentException("Key cannot be null");
        
        int i;                                                          // Index of the first empty slot
        for (i = hash(key); keys[i] != null; i = (i + 1) % capacity) {  // While the slot is not empty
            if (keys[i].equals(key))                                    // If the key is already in the table
                return;
        }
        
        keys[i] = key;                                                  // Insert the <K, V> pair in the table
        values[i] = value;
        size++;
    }

    public V get(K key) {
        if (key == null) throw new IllegalArgumentException("Key cannot be null");
        
        for (int i = hash(key); keys[i] != null; i = (i + 1) % capacity)
            if (keys[i].equals(key))
                return values[i];
        
        return null;
    }// Try finding the key. If it is not found, return null.

    public void remove(K key){
        if (key == null) throw new IllegalArgumentException("Key cannot be null");
        if (!contains(key)) return;      // If the key is not in the table, return

        int i = hash(key);
        while (!key.equals(keys[i])) {   // While the key is not in the table
            i = (i + 1) % capacity;     // Move to the next slot
        }

        keys[i] = null;                 // Nullify the key, value pair values
        values[i] = null;

        i = (i + 1) % capacity;         // Move to the next slot
        while (keys[i] != null) {
            K keyToReadd = keys[i];     // Move all the keys and values to the left
            V valToReadd = values[i];   // to fill the empty slot in the table
            keys[i] = null;
            values[i] = null;
            size--;
            put(keyToReadd, valToReadd);
            i = (i + 1) % capacity;
        }

        size--;
    }
}
