/**
 * Stack (Linked List implementation)
 *
 * author: @Dion
 * content: A stack implemented with Linked Lists.
 * basic functions: push, pop, isEmpty
 *
 * Space Complexity: O(n) (40 bytes per node)
 * Search: O(n)
 * Insertion: O(1)
 * Deletion: O(1)
 * Size: O(1)
 *
 * Pros: No pre-defined size, can grow infinitely, constant time in the worst case for insertion/deletion.
 * Cons: Object overhead per node.
 **/


public class stackLinkedList<T> {
    private int size;
    private Node<T> first;
    
    public stackLinkedList() {
        this.size = 0;
        this.first = null;
    }

    public void push(T data){
        first = new Node<T>(data, first);
        size++;
    }
    
    public T pop(){
        if (isEmpty()) throw new RuntimeException("Stack is empty");
        T data = first.data;
        first = first.next;
        size--;
        return T;
    }
    
    public int size(){
        return size;
    }
    
    public boolean isEmpty(){
        return first == null;
    }
}
