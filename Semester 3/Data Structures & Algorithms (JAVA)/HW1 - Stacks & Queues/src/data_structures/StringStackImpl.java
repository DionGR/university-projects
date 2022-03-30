package data_structures;

import data_structures.interfaces.StringStack;      // Import the given Stack interface from the data_structures.interfaces package.
import java.io.PrintStream;
import java.util.NoSuchElementException;

/**
 * This is a Generic Stack implementation using a raw Linked List (only Nodes).
 * It implements the StringStack interface, that was altered to be Generic.
 *
 * @authors: P3200262, P3200298
 * @info: Made for the course of Data Structures @ AUEB 2021-2022
 *
 * Insertion: O(1)
 * Deletion: O(1)
 * Size: O(1)
 * Print: O(n)
 **/

public class StringStackImpl<T> implements StringStack<T> {
    private Node<T> first;                            // Stack pointer.
    private int size;
    
    @Override
    public void push(T data) {
        this.first = new Node<T>(data, this.first);  // Add a new Node to the top of the stack, pushing the previous first Node further down.
        this.size++;
    }// Push to the top of the stack.
    
    @Override
    public T pop() throws NoSuchElementException {
        if(this.first == null) throw new NoSuchElementException("The stack is empty!");
        
        T data = this.first.data;                   // Hold the data of the first Node.
        this.first = this.first.next;               // Pop the node from the stack by moving the stack pointer further down.
        this.size--;
        return data;
    }// Pop from the top of the stack and return the data.
    
    @Override
    public T peek() throws NoSuchElementException {
        if(this.first == null) throw new NoSuchElementException("The stack is empty!");
        
        return this.first.data;
    }// Return the data of the first Node without popping.
    
    @Override
    public void printStack(PrintStream stream) {
        if (isEmpty()){
            stream.println("The stack is empty!");
        } else{
        
            Node<T> current = this.first;
            
            while (current != null) {
                stream.print(current.data + " -> ");
                current = current.next;
            }// Traverse the stack and print the data of each Node.
            
            stream.println("Null");                 // Final element points to null.
        }
    }
    
    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.first == null;
    }
}
