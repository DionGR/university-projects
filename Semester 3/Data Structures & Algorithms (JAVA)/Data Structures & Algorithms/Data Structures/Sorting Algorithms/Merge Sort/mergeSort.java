/**
 * @author: Dion
 * @content: A Merge Sort algorithm that sorts an array of integers.
 * Unsorted Array -> Sorted Array
 *
 * Space Complexity: O(n)
 * O(n): O(n log(n))
 * Θ(n): Θ(n log(n))
 * Ω(n): Ω(n log(n))
 * Exchanges: 6nlog(n)
 * Comparisons: nlog(n)
 *
 * Optimal for: Arrays of size n >=~ 10, else Insertion Sort.
 * Has high overhead and is stable.
 * Is used for object sorting because it is assumed that there is memory to spare.
 **/

public class mergeSort {
    private static final int CUTOFF = 7;                // Cutoff to insertion sort.
    
    public static void sort(String[] array){
        String[] aux = array.clone();                   // Create auxiliary array, clone of array.
        sort(aux, array, 0, array.length - 1);
    }
    
    private static void sort(String[] array, String[] aux, int low, int high) {
        if (high <= low + CUTOFF){
            insertionSort.sort(aux, low, high);
            return;
        }                                               // If the array is small enough, use insertion sort.
        
        int mid = low + (high - low) / 2;
        sort(aux, array, low, mid);                     // Sort the left half.
        sort(aux, array, mid + 1, high);            // Sort the right half.
        if (array[mid].compareTo(array[mid+1]) <= 0){       // If it's already sorted, save merge time.
            System.arraycopy(array, low, aux, low, high - low + 1);
            return;
        }
        merge(array, aux, low, mid, high);              // Merge the two halves.
    }
    
    private static void merge(String[] array, String[] aux, int low, int mid, int high){
        int i = low, j = mid + 1;                                       // Set the indexes.
        
        for (int k = low; k <= high; k++){
            if      (i > mid)                  aux[k] = array[j++];     // If the left array is empty, copy the right array.
            else if (j > high)                 aux[k] = array[i++];     // If the right array is empty, copy the left array.
            else if (less(array[j], array[i])) aux[k] = array[j++];     // If the right element is less than the left element, copy the right element.
            else                               aux[k] = array[i++];     // If the left element is less than the right element, copy the left element.
        }
    }
    
    private static boolean less(String first, String second) {
        return first.compareTo(second) < 0;
    }
    
    public static void bottomUpSort(String[] array){
        int N = array.length;
        String[] aux = new String[N];
        for (int sz = 1; sz < N; sz = sz + sz)
            for (int lo = 0; lo < N - sz; lo += sz + sz)
                merge(array, aux, lo, lo + sz - 1, Math.min(lo + sz + sz - 1, N - 1));
    } // Simple and 10% slower than recursive.
}
