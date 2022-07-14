/**
 * author: @Dion
 * content: A Shell Sort algorithm that sorts an array of items.
 * <Object> Array -> Sorted <Object> Array
 *
 * Space Complexity: O(1)
 * O(n): O(nlog(n)^2)
 * Θ(n): Θ(nlog(n)^2)
 * Ω(n): Ω(nlog(n))
 *
 * Optimal for: Small sub-arrays, embedded systems (small code footprint), hardware sort.
 **/

public class shellSort {
    
    public static <Item extends Comparable<Item>> void Sort(Item[] array){
        int length = array.length;
        
        int h = 1;
        while(h < length/3) h = 3*h + 1; // 3x + 1 increment sequence
        
        while(h >= 1){
            for(int i = h; i < length; i++){
                for(int j = i; j >= h && less(array[j], array[j - h]); j -= h)
                    exchange(array, j, j-h);
            } // Insertion sort.
            h = h/3; // Move to the next increment.
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
