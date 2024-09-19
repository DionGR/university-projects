package data_structures;

/**
 * PART C:  Heapsort.
 *
 * Implementation of a heapsort algorithm that sorts an array of
 * elements in descending order. The objects have to implement the
 * Comparable interface.
 *
 * @authors: P3200262, P3200298
 * @info: Made for the course of Data Structures @ AUEB 2021-2022
 **/

@SuppressWarnings({"rawtypes", "unchecked"})                // Suppress warnings about using raw types or unchecked casts.
public class Sort{
    
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
