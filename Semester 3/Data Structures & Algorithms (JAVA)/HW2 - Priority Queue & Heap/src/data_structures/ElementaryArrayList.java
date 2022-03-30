package data_structures;

/**
 * PART A: Custom ArrayList.
 *
 * Implement a custom ArrayList using only specific features
 * that will be needed for this assignment. It replicates the
 * functionality of the Java ArrayList class.
 *
 * @authors: P3200262, P3200298
 * @info: Made for the course of Data Structures @ AUEB 2021-2022
 **/

@SuppressWarnings("unchecked")      // Suppress warnings about unchecked casting operations.
public class ElementaryArrayList<T>  {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] array;
    private int size;
    
    public ElementaryArrayList() {
        this.array = (T[]) new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }
    
    public void add(T item) {
        if (size == array.length) resize(array.length * 2);     // Resize the array if it is full.
        array[size++] = item;                                          // Add the item to the end of the array.
    }// Adds item to the end of the array.
    
    @Deprecated // This method is not used in the assignment.
    public void remove(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException("Index is out of bounds!");
        System.arraycopy(array, index + 1, array, index, size - 1 - index); // Shift the array to the left.
        array[--size] = null;                                                           // Remove the last element.
        if (size > 0 && size == array.length / 4) resize(array.length / 2);     // Shrink the ArrayList if it's only a quarter-full.
    }// Remove an item by index.
    
    public T get(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        return array[index];
    }// Returns the item at given index.
    
    public int size() {
        return size;
    }// Returns the size of the array.
    
    private void resize(int newSize){
        T[] copy = (T[]) new Object[newSize];
        if (size >= 0) System.arraycopy(array, 0, copy, 0, size);
        array = copy;
    }// HELPER - Resize the array.
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            sb.append(array[i]).append(" ");
        }
        return sb.toString();
    }// Returns a string representation of the arraylist.
}

