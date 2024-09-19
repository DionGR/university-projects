/**
 * @author: Dion
 * @content: An insertion sort algorithm that sorts an array of objects.
 * <Object> Array -> Sorted <Object> Array
 *
 * Space Complexity: O(1)
 * O(n): O(n^2)
 * Θ(n): Θ(n^2)
 * Ω(n): Ω(n)
 * Exhchanges: O(n^2)
 * Comparisons: n(n-1)/2 (Worst Case)
 *
 * Optimal for: Arrays of size n <= ~ 90 or semi-sorted arrays (Checking if something is sorted).
 * Has very low overhead and is stable.
 */

public class insertionSort{
    
    public static <Item extends Comparable<Item>> void sort(Item[] array){
        int length = array.length;
        for(int i = 1; i < length; i++){
            for(int j = i; j > 0; j--){
                if(less(array[j], array[j-1])) exchange(array, j, j - 1);
                else break;
            }
        }
    }
    
    public static <Item extends Comparable<Item>> void sort(Item[] array, int start, int end){
        for(int i = start; i <= end; i++){
            for(int j = i; j > start; j--){
                if(less(array[j], array[j-1])) exchange(array, j, j - 1);
                else break;
            }
        }
    }

    public static <Item extends Comparable<Item>> void partialSort(Item[] array, int start, int end){
        for(int i = start + 1; i < end; i++){
            for(int j = i; j > start; j--){
                if(less(array[j], array[j-1])) exchange(array, j, j - 1);
                else break;
            }
        }
    }

    public static <Item extends Comparable<Item>> void sortImproved(Item[] array){
        int length = array.length;
        for (int i = length - 1; i > 0; i--){
            if(less(array[i], array[i-1])) exchange(array, i, i - 1);
        }
        for (int i = 2; i < length; i++){
            int j = i;
            Item temp = array[i];
            while(less(temp, array[j-1])){
                array[j] = array[j-1];
                j--;
            }
            array[j] = temp;
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
