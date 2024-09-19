package data_structures;

import data_structures.interfaces.IntQueue; // Import the IntQueue interface from the data_structures.interfaces package.
import java.io.PrintStream;
import java.util.NoSuchElementException;


/**
 * This is a Generic Queue implementation using a raw Linked List (only Nodes).
 * It implements the IntQueue interface, that was altered to be Generic.
 *
 * @authors: P3200262, P3200298
 * @info: Made for the course of Data Structures @ AUEB 2021-2022
 *
 * Insertion: O(1)
 * Deletion: O(1)
 * Size: O(1)
 * Print: O(n)
 **/

public class IntQueueImpl<T> implements IntQueue<T> {
    private Node<T> head, tail;
    private int size;
    
    @Override
    public void put(T data) {
        Node<T> old_tail = this.tail;               // Save the old tail node.
        this.tail = new Node<T>(data, null);   // Create a new node with the data and null next.
        if(isEmpty()) this.head = this.tail;        // If the queue is empty, the new node is the head.
        else old_tail.next = this.tail;             // Else, the old tail node's next is the new node.
        
        this.size++;
    }
    
    @Override
    public T get() throws NoSuchElementException {
        if(this.head == null) throw new NoSuchElementException("The queue is empty!");
        
        T data = this.head.data;        // Save the data of the head node.
        this.head = this.head.next;     // Set the head node to the next node.
        this.size--;
        return data;                    // Return the data of the old head node.
    }
    
    @Override
    public T peek() throws NoSuchElementException {
        if(this.head == null) throw new NoSuchElementException("The queue is empty!");
        
        return this.head.data;
    }
    
    @Override
    public void printQueue(PrintStream stream) {
    
        if (isEmpty()) {
            stream.println("The queue is empty!");
        } else{
        
            Node<T> current = this.head;
        
            stream.print("Head Node");
            while (current != null) {
                stream.print(" -> " + current.data);
                current = current.next;
            }
            System.out.println(" <- Tail Node");
        }// Traverse the queue and print the data of each Node.
    }
    
    @Override
    public int size() {
        return this.size;
    }
    
    @Override
    public boolean isEmpty() {
        return this.head == null;
    }
}
