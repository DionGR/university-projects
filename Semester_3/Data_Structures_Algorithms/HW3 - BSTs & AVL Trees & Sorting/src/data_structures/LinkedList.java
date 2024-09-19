package data_structures;

import java.util.NoSuchElementException;

/**
 * This is Generic Raw Linked List (only Nodes)
 * Will be used as a list for our stopword list
 * and as a stack for tree traversal.
 *
 * @authors: P3200262
 * @info: Made for the course of Data Structures @ AUEB 2021-2022
 *
 * Insertion: O(1)
 * Deletion: O(1)
 * Size: O(1)
 * Print: O(n)
 **/


public class LinkedList<Value extends Comparable<Value>>{
    private final Node head;
    private int size;
    
    public class Node{
        public Value value;
        public Node next;
        
        public Node(){
            this(null, null);
        }
        
        public Node(Value value, Node next){
            this.value = value;
            this.next = next;
        }
    }
    
    public LinkedList(){
        this.head = new Node();
        this.size = 0;
    }
    
    public void insert(Value value){
        head.next = new Node(value, head.next);
        ++size;
    }
    
    public void remove(Value value){
        if (head.next == null) throw new NoSuchElementException("The list is empty!");
        
        Node prev = head;
        
        for (Node current = head.next; current.next != null; current = current.next){
            if (current.value == value){
                prev.next = current.next;
                --size;
            }
            prev = current;
        }
    }
    
    public Value pop(){
        if (head.next == null) throw new NoSuchElementException("The list is empty!");
        
        Value to_pop = head.next.value;
        head.next = head.next.next;
        --size;
        return to_pop;
    }
    
    public Value get(int index){
        if (index > size) throw new IndexOutOfBoundsException("The index is out of bounds!");
        
        Node current = head.next;
        while(index > 0){
            --index;
            current = current.next;
        }
        
        return current.value;
    }
    
    public boolean search(Value value){
        for (Node current = head.next; current != null; current = current.next)
            if (current.value.compareTo(value) == 0) return true;
        
        return false;
    }
    
    public boolean isEmpty(){
        return head.next == null;
    }
    
    public int size(){
        return size;
    }
}
