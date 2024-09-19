package data_structures;

/**
 * Doubly Linked List
 *
 * A Doubly Linked List used to re-arrange the priority of and access our data
 * in constant O(1) time without having to resort to a heap.
 *
 * Hashmap points to nodes in the doubly linked list, which are either removed (if LRU)
 * or reinserted at the top (if used recently).
 *
 * Insertion/Removal: O(1)
 * Access: O(1)
 * Size: O(1)
 * Search: O(n) BUT O(1) due to HashMap reference
 *
 * @author P3200262
 * @info Made for the course of Data Structures @ AUEB 2021-2022 as a BONUS assignment
 **/

public class DoublyLinkedList<T>{
    
    private final Node<T> head;
    private final Node<T> tail;
    private int size;
    
    public DoublyLinkedList(){
        this.head = new Node<>(null);
        this.tail = new Node<>(null);
        this.size = 0;
    }
    
    /**
     * Push an existing node to the top of the list
     */
    public void push(Node<T> node){
        if (node == null) throw new NullPointerException();
        
        if (head.next == null){
            head.next = node;
            tail.prev = node;
            node.prev = head;
            node.next = tail;
        } else {
            node.next = head.next;
            head.next.prev = node;
            node.prev = head;
            head.next = node;
        }
        
        ++size;
    }
    
    /**
     * Reinsert that node at the top of the list
     */
    public void reinsert(Node<T> node) {
        if (node == null) throw new NullPointerException();
        
        removeNode(node);
        push(node);
    }
    
    /**
     * Remove a node from the list
     */
    public void removeNode(Node<T> node) {
        if (node == null) throw new NullPointerException();
        
        if (node.prev != null)
            node.prev.next = node.next;
        if (node.next != null)
            node.next.prev = node.prev;
        
        --size;
    }
    
    /**
     * Get the node at the bottom of the list (LRU)
     */
    public T popLast() {
        if (head.next == null) throw new NullPointerException();
        
        Node<T> popped = tail.prev;
        
        tail.prev = popped.prev;
        popped.prev.next = tail;
        
        --size;
        
        return popped.data;
    }
    
    public int size() {
        return size;
    }
}
