/**
 * Heap Sort
 *
 * @author: Dion
 * @content: Heap Sort
 *
 * Space Complexity: O(1)
 * O(n): O(n log(n))
 * Θ(n): Θ(n log(n))
 * Ω(n): Ω(n log(n))
 *
 * In place, unstable and poor use of cache memory. Inner loop longer than quicksort.
 */

public class heapSort {
    
    public static void sort(Comparable[] array) {
        int size = array.length;
        for (int k = size/2; k >= 1; k--)                   // Constructs the heap.
            sink(array, k, size);                           // From the bottom up.
        while (size > 1) {                                  // Sink the smallest element
            exchange(array, 1, size--);                   // to the bottom of the heap
            sink(array, 1, size);                         // and sink the first element.
        }
    }
    
    public static void sink(Comparable[] array, int k, int size) {
        while (2*k <= size) {
            int j = 2*k;                                                // j is the left child of k.
            if (j < size && greater(array, j, j + 1)) j++;            // Index j becomes the smaller child.
            if (!greater(array, k, j)) break;                           // If k is not greater than j, break.
            exchange(array, k, j);                                      // Exchange k with j.
            k = j;
        }
    }
    
    public static void exchange(Comparable[] array, int i, int j) {
        Comparable temp = array[i - 1];
        array[i - 1] = array[j - 1];
        array[j - 1] = temp;
    } // HELPER - Exchange two items in the array. Index - 1 because array is 0-indexed but heap is 1-indexed.
    
    private static boolean greater(Comparable[] array, int i, int j) {
        return array[i - 1].compareTo(array[j - 1]) > 0;
    }// HELPER - Checks if left is greater than right. Index - 1 because array is 0-indexed but heap is 1-indexed.
}
