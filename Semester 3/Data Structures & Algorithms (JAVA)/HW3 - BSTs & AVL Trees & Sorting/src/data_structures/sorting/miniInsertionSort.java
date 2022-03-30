package data_structures.sorting;

/**
 * Mini Insertion Sort to aid Merge Sort in small cases
 *
 * @authors: P3200262
 * @info: Made for the course of Data Structures @ AUEB 2021-2022
 *
 * Space Complexity: O(1)
 * O(n): O(n^2)
 * Θ(n): Θ(n^2)
 * Ω(n): Ω(n)
 *
 * Optimal for: Arrays of size n <= ~ 90 or semi-sorted arrays (Checking if something is sorted).
 * Has very low overhead and is stable.
 */

public class miniInsertionSort {
    
    public static <Item extends Comparable<Item>> void sort(Item[] array, int start, int end){
        for(int i = start; i <= end; i++){
            for(int j = i; j > start; j--){
                if(less(array[j], array[j-1])) exchange(array, j, j - 1);
                else break;
            }
        }
    }
    public static <Item extends Comparable<Item>> boolean less(Item first, Item second) {
        return first.compareTo(second) < 0;
    }
    
    public static <Item extends Comparable<Item>> void exchange(Item[] array, int i, int j) {
        Item temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
