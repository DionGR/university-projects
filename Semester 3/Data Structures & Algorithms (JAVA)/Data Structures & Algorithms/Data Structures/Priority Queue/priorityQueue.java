/**
 * Priority Queue
 *
 * @author: Dion
 * @content: Priority Queue with Binary Heap (max)
 *
 * Space Complexity: O(n)
 * Search: O(log n)
 * Insert: O(log n)
 * Delete: O(log n)
 * Size: O(1)
 */

@SuppressWarnings("unchecked")
public final class priorityQueue<T extends Comparable<T>> {
    private T[] pq;
    private int size;
    
    public priorityQueue(){
        this.pq = (T[]) new Comparable[4];
        this.size = 0;
    }
    
    public void insert(T item) {
        if (size >= (pq.length * 0.75)) resize(2 * pq.length); // Double capacity of array if size is 75% of capacity.
        
        pq[++size] = item;                                             // Insert the item to the priority queue.
        swim(size);                                                    // Swim the item to its correct position.
    }
    
    public T delMax(){
        if(isEmpty()) throw new IllegalStateException("Priority queue is empty.");
    
        T max = pq[1];                                      // Get the max item.
        exchange(1, size);                                // Exchange the max item with the last item.
        pq[size--] = null;                                  // Nullify the last item.
        sink(1);                                          // Sink the new max item to its correct position.
        return max;
    }
    
    public T getMax(){
        if(isEmpty()) throw new IllegalStateException("Priority queue is empty.");
        
        return pq[1];
    }// Get the max item.
    
    public void swim(int k){
        while(k > 1 && less(k/2, k)){     // While k is not the root and the parent is less than k. (parent == k/2)
            exchange(k, k/2);             // Exchange k with its parent.
            k = k/2;                        // K is now the parent, so divide by 2 from its original value.
        }
    }
    
    public void sink(int k){
        while(2*k <= size){
            int j = 2*k;                                // j is the left child of k.
            if(j < size && less(j, j + 1)) j++;       // If k has a right child and the right child is smaller than the left child, j+1 is the right child.
            if(!less(k, j)) break;                      // If k is not less than j, break.
            exchange(k, j);                             // Exchange k with j.
            k = j;
        }
    }
    
    public boolean isEmpty(){
        return size == 0;
    }// Check if the priority queue is empty.
    
    private boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }// HELPER - Checks if left is less than right.
    
    private void exchange(int i, int j) {
        T t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;
    }// HELPER - Exchange two items in the array.
    
    private void resize(int new_size){
        T[] copy = (T[]) new Comparable[new_size];
        if (size >= 0) System.arraycopy(pq, 0, copy, 0, this.size);
        pq = copy;
    }// HELPER - Resize the array.
}
