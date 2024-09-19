import java.util.Iterator;

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
 * Pros: No pre-defined size, can grow infinitely, constant time in the worst case for insertion and deletion.
 * Cons: Object overhead per node.
 **/

public class stackIteratorLinkedList<T> implements Iterable<T>{
    
    private int size;
    private Node<T> first;
    
    public stackIteratorLinkedList(){
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
    
    public Iterator<T> iterator() { return new ListIterator(); }
    
    private class ListIterator implements Iterator<T> {
        private Node<T> current = first;
        
        @Override
        public boolean hasNext() { return current.next != null; }
        
        @Override
        public T next(){
            T data = current.data;
            current = current.next;
            return data;
        }
    
        @Override
        @Deprecated
        public void remove() { throw new UnsupportedOperationException(); }
    }
}
