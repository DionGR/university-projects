package components;

import components.interfaces.Cache;
import data_structures.*;

/**
 * LRUCache
 *
 * Our LRU Cache implementation.
 * Utilizes a Linear Probing Hash Table and a Doubly Linked List.
 *
 * NOTE: Any redundant (AKA not used in this assignment) code has been cleared
 * out for readability. This applies for all the classes in this assignment.
 *
 * @author P3200262
 * @info Made for the course of Data Structures @ AUEB 2021-2022 as a BONUS assignment
 **/

public class LRUCache<K, V> implements Cache<K, V> {
    private final HashTable<K, Node<Data<K, V>>> cache_map;
    private final DoublyLinkedList<Data<K, V>> cache;
    
    private final int capacity;
    private long lookups;
    private long hits;
    private long misses;
    
    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new DoublyLinkedList<>();
        this.cache_map = new FixedHashTableLP<>(capacity * 3);
    }
    
    public LRUCache() {
        this(100);
    }
    
    
    @Override
    public V lookUp(K key) {
        Node<Data<K, V>> node = cache_map.get(key);
        
        ++lookups;
        
        if (node == null) {
            ++misses;
            return null;
        }// If the key is not in the cache return null
        
        ++hits;
        cache.reinsert(node);   // Reinsert the node in the cache with top priority
        
        return node.data.getValue();
    }
    
    @Override
    public void store(K key, V value) {
        Node<Data<K, V>> node = new Node<>(new Data<>(key, value));
        
        // If the cache is full, evict the least recently used element
        if (cache.size() >= capacity || cache_map.size() >= capacity)
            cache_map.remove(cache.popLast().getKey());
        
        // Insert the new node in the cache with top priority
        cache.push(node);
        cache_map.put(key, node);
    }
    
    @Override
    public double getHitRatio() {
        return hits / (double) lookups;
    }
    
    @Override
    public long getHits() {
        return hits;
    }
    
    @Override
    public long getMisses() {
        return misses;
    }
    
    @Override
    public long getNumberOfLookUps() {
        return lookups;
    }
}

