/**
 * Stack
 *
 * author: @Dion
 * content: A stack implemented with an array.
 * basic functions: push, pop, isEmpty
 *
 * Space Complexity: O(n)
 * Search: O(n)
 * Insertion: O(1) - O(n) when resizing
 * Deletion: O(1) - O(n) when resizing
 * Size O(1)
 *
 * Pros: No object overhead per node, operations take constant amortized time.
 * Cons: Overflows when it reaches capacity, unless resize is implemented.
 **/


public class stackArray {
    private String[] array;
    private int size;
    
    public stackArray(){
        this.array = new String[1];
    }
    
    public void push(String item){
        if (size == array.length) resize(2 * array.length);
        if (size == array.length/4) resize(array.length/2);
        array[size++] = item;
    }
    
    public String pop(){
        if (isEmpty()) throw new IllegalStateException("Stack underflow");
        String item = array[--size];
        array[size] = null;
        return item;
    }
    
    public int size(){
        return size;
    }
    
    public boolean isEmpty(){
        return size == 0;
    }
    
    public void resize(int new_size){
        String[] copy = new String[new_size];
        if (size >= 0) System.arraycopy(array, 0, copy, 0, size);
        array = copy;
    }
    
}
