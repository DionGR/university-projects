/**
 * @author: @dion
 * @content: A Quick Sort algorithm that sorts an array of items.
 * <Object> Array -> Sorted <Object> Array
 *
 * Space Complexity: O(log(n))
 * O(n): O(n^2)
 * Θ(n): Θ(n log(n))
 * Ω(n): Ω(n log(n))
 *
 * Optimal for: Smaller data structures.
 * Good cache locality, in-place with low overhead but unstable.
 * Faster on average, 2-3 times than merge sort or heap sort. Runs on Θ if shuffled.
 * Worst case only occurs when the array is already sorted, very low probability.
 */


public class quickSort {
    private static final int CUTOFF = 10;
    
    
    public static <Item extends Comparable<Item>> void sort(Item[] array) {
        shuffle(array);
        sort(array, 0, array.length - 1);
    }
    
    public static <Item extends Comparable<Item>> void sort(Item[] array, int low, int high) {
        if(high <= low + CUTOFF - 1){
            insertionSort.partialSort(array, low, high);
            return;
        }// If the array is small enough, use insertion sort.
        
//        int median = getMedian(array, low, high);
//        exchange(array, low, median);

        int j = partition(array, low, high);                                     // Partition the array.
        sort(array, low, j - 1);                                            // Sort the left side of the array.
        sort(array, j + 1, high);                                           // Sort the right side of the array.
    }
    
    /**
     * Three-Way Quicksort - Used for multiple duplicate elements.
     */
    private static <Item extends Comparable<Item>> void threeWaySort(Item[] array, int low, int high) {
        if (high <= low) return;
        int lt = low, gt = high;
        Item v = array[low];
        int i = low;
        while (i <= gt)
        {
            int cmp = array[i].compareTo(v);
            if (cmp < 0)        exchange(array, lt++, i++);
            else if (cmp > 0)   exchange(array, i, gt--);
            else i++;
        }
        sort(array, low, lt - 1);
        sort(array, gt + 1, high);
    }
    
    public static <Item extends Comparable<Item>> int partition(Item[] array, int low, int high){
        int i = low, j = high + 1;
        
        while(true){                                // Loop until the partition is complete.
            while(less(array[++i], array[low]))     // Find the first element that is greater than the pivot.
                if (i == high) break;
            while (less(array[low], array[--j]));    // Find the first element that is less than the pivot.
                
            if(i >= j) break;                       // If the first element is greater than the pivot, break.
            exchange(array, i, j);
        }
        exchange(array, low, j);
        return j;
    }
    
    
    private static <Item extends Comparable<Item>> int getMedian(Item[] array, int low, int high){
        if (high - low <= 5)
            return low + (high - low)/2;
        else
            return medianOfThree(array, low, low + (high - low)/2, high);  // Find the median of the array.
    }
    private static <Item extends Comparable<Item>> int medianOfThree(Item[] array, int low, int mid, int high) {
        if (less(array[mid], array[low])) {
            exchange(array, low, mid);
        }
        if (less(array[high], array[mid])) {
            exchange(array, mid, high);
            if (less(array[mid], array[low])) {
                exchange(array, low, mid);
            }
        }
        return mid;
    }
    
    public static <Item extends Comparable<Item>> boolean less(Item first, Item second) {
        return first.compareTo(second) < 0;
    }
    public static <Item extends Comparable<Item>> void exchange(Item[] array, int i, int j) {
        Item temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
    
    public static void shuffle(Object[] a) {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            int r = (int) (Math.random() * (i + 1));
            Object swap = a[r];
            a[r] = a[i];
            a[i] = swap;
        }
    }// Shuffles an array to ensure randomness of elements and average case.
}
