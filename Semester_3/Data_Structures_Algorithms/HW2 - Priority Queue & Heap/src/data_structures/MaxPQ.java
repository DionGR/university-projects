package data_structures;

import data_structures.interfaces.PQInterface;

/**
 * PART A: Priority Queue using a max-heap.
 *
 * Insertion: 0(log n)
 * Removal: 0(log n)
 * Size, isEmpty, Max: 0(1)
 *
 * @authors: P3200262, P3200298
 * @info: Made for the course of Data Structures @ AUEB 2021-2022
 **/


public class MaxPQ <T extends Comparable<T>> implements PQInterface<T> {
    private T[] PQ;
    private int size;
    
    @SuppressWarnings("unchecked")
    public MaxPQ(int capacity) {
        this.PQ = (T[]) new Comparable[capacity + 1];
        this.size = 0;
    }// Main Constructor.
    
    public MaxPQ(){
        this(4);
    }// Default Constructor in case no capacity is passed.
    
    public void swim(int k){
        while(k > 1 && less(k/2, k)){   // While k is not the root and the parent is less than k. (parent == k/2)
            exchange(k, k/2);             // Exchange k with its parent.
            k = k/2;                        // K is now the parent, so divide by 2  from its original value.
        }
    }

    public void sink(int k){
        while(2*k <= size){                             // While k has children
            int j = 2*k;                                // j is the left child of k.
            if(j < size && less(j, j + 1)) j++;    // If k has a right child and the left child is smaller than the right child, j++ to get the right child.
            if(!less(k, j)) break;                      // If the parent is not less than the bigger child, break.
            exchange(k, j);                             // Exchange k with j.
            k = j;
        }
    }
    
    @Override
    public void insert(T item) {
        if (size >= (PQ.length * 0.75)) resize(2 * PQ.length); // Double capacity of array if size is 75% of capacity.
        PQ[++size] = item;                                               // Insert the item to the priority queue.
        swim(size);                                                      // Swim the item to its correct position.
    }
    
    @Override
    public T getMax() {
        if(isEmpty()) throw new IllegalStateException("Priority queue is empty.");
        
        T max = PQ[1];                                      // Get the max item.
        exchange(1, size);                                // Exchange the max item with the last item.
        PQ[size--] = null;                                  // Nullify the last item.
        sink(1);                                          // Sink the new max item to its correct position.
        return max;
    }
    
    @Override
    public T max() {
        if(isEmpty()) throw new IllegalStateException("Priority queue is empty.");
        
        return PQ[1];
    }// Get the max item.
    
    @Override
    public int size() {
        return size;
    }// Get the size of the priority queue.
    
    @Override
    public boolean isEmpty() {
        return size == 0;
    }// Check if the priority queue is empty.
    
    private boolean less(int left, int right) {
        return PQ[left].compareTo(PQ[right]) < 0;
    }// HELPER - Checks if left is less than right.
    
    private void exchange(int i, int j) {
        T temp = PQ[i];
        PQ[i] = PQ[j];
        PQ[j] = temp;
    }// HELPER - Exchange two items in the array.
    
    @SuppressWarnings("unchecked")
    private void resize(int newCapacity){
        T[] copy = (T[]) new Comparable[newCapacity];
        if (size >= 0) System.arraycopy(PQ, 0, copy, 0, PQ.length);
        PQ = copy;
    }// HELPER - Resize the array.
}
