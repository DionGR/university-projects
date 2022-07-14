package data_structures;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * Queue (Linked List implementation)
 *
 * author: @Dion
 * content: A queue implemented with a linked list.
 * basic functions: enqueue, dequeue, isEmpty
 *
 * Space Complexity: O(n)
 * Search: O(n)
 * Insertion: O(1)
 * Deletion: O(1)
 * Size O(1)
 *
 * Pros: No pre-defined size, can grow infinitely, constant time in the worst case.
 * Cons: Object overhead per node.
 **/


public class queueLinkedList<T> {
    private Node<T> head, tail;
    private int size;
    
    
    public void enqueue(T data) {
        Node<T> old_tail = tail;               // Save the old tail node.
        tail = new Node<T>(data, null);        // Create a new node with the data and null next.
        if(isEmpty()) head = tail;        // If the queue is empty, the new node is the head.
        else old_tail.next = tail;             // Else, the old tail node's next is the new node.
        
        size++;
    }
    
    public T dequeue() throws NoSuchElementException {
        if(head == null) throw new NoSuchElementException("The queue is empty!");
        
        T data = head.data;        // Save the data of the head node.
        head = head.next;     // Set the head node to the next node.
        size--;
        return data;                    // Return the data of the old head node.
    }
    
    public T peek() throws NoSuchElementException {
        if(head == null) throw new NoSuchElementException("The queue is empty!");
        
        return head.data;
    }
    
    public int size() {
        return size;
    }
    
    public boolean isEmpty() {
        return head == null;
    }
}
