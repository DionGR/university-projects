package data_structures.sorting;

/**
 * A Merge Sort Implementation
 * Learned from Algorithms Fourth Edition - Robert Sedgewick & Kevin Wayne English Edition,
 * implemented by me.
 *
 * @authors: P3200262
 * @info: Made for the course of Data Structures @ AUEB 2021-2022
 *
 * Space Complexity: O(n)
 * O(n): O(n log(n))
 * Θ(n): Θ(n log(n))
 * Ω(n): Ω(n log(n))
 *
 * Optimal for: Arrays of size n >=~ 7, else Insertion Sort.
 * Has high overhead and is stable.
 * Is used for object sorting because it is assumed that there is memory to spare.
 **/

public class mergeSort {
    private static final int CUTOFF = 7;                // Cutoff to insertion sort.
    
    public static <T extends Comparable<T>> void sort(T[] array){
        T[] aux = array.clone();                        // Create auxiliary array, clone of array.
        sort(aux, array, 0, array.length - 1);
    }
    
    private static <T extends Comparable<T>> void sort(T[] array, T[] aux, int low, int high) {
        if (high <= low + CUTOFF){
            miniInsertionSort.sort(aux, low, high);
            return;
        }                                                   // If the array is small enough, use insertion sort.
        
        int mid = low + (high - low) / 2;
        sort(aux, array, low, mid);                         // Sort the left half.
        sort(aux, array, mid + 1, high);                // Sort the right half.
        
        if (array[mid].compareTo(array[mid+1]) <= 0){       // If it's already sorted, save merge time.
            System.arraycopy(array, low, aux, low, high - low + 1);
            return;
        }
        merge(array, aux, low, mid, high);                  // Merge the two halves.
    }
    
    private static <T extends Comparable<T>> void merge(T[] array, T[] aux, int low, int mid, int high){
        int i = low, j = mid + 1;                                       // Set the indexes.
        
        for (int k = low; k <= high; k++){
            if      (i > mid)                  aux[k] = array[j++];     // If the left array is empty, copy the right array.
            else if (j > high)                 aux[k] = array[i++];     // If the right array is empty, copy the left array.
            else if (less(array[j], array[i])) aux[k] = array[j++];     // If the right element is less than the left element, copy the right element.
            else                               aux[k] = array[i++];     // If the left element is less than the right element, copy the left element.
        }
    }
    
    private static <T extends Comparable<T>> boolean less(T first, T second) {
        return first.compareTo(second) < 0;
    }
}

