import java.util.Iterator;
/**
 * Stack
 *
 * @author: Dion
 * @content: A stack implemented with an array.
 * basic functions: push, pop, isEmpty
 *
 * Space Complexity: O(n) (8N bytes when full, 32N bytes when quarter full)
 * Search: O(n)
 * Insertion: O(1) - O(n) when resizing
 * Deletion: O(1) - O(n) when resizing
 * Size O(1)
 *
 * Pros: No object overhead per node, operations take constant amortized time.
 * Cons: Overflows when it reaches capacity, unless resize is implemented.
 **/


public class stackIteratorArray<T> {
    private T[] array;
    private int size;
    
    public stackIteratorArray(){
        this.array = (T[]) new Object[1];
        this.size = 0;
    }
    
    public void push(T data){
        if (size == array.length) resize(2 * array.length);
        if (size == array.length/4) resize(array.length/2);
        array[size++] = data;
    }
    
    public T pop(){
        if (isEmpty()) throw new RuntimeException("Stack is empty");
        T data = array[--size];
        array[size] = null;
        return data;
    }
    
    public int size(){
        return size;
    }
    
    public boolean isEmpty(){
        return size == 0;
    }
    
    private void resize(int new_size){
        T[] copy = (T[]) new Object[new_size];
        if (size >= 0) System.arraycopy(array, 0, copy, 0, size);
        array = copy;
    }
    
    public Iterator<T> iterator() {
        return new ReverseArrayIterator();
    }
    
    private class ReverseArrayIterator implements Iterator<T>
    {
        private int i = size;
        
        @Override
        public boolean hasNext() { return i > 0; }

        @Override
        @Deprecated
        public void remove() { /* not supported */ }

        @Override
        public T next() { return array[--i]; }
    }    
}

