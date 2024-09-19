/**
 * author: @Dion
 * content: A Selection Sort algorithm that sorts an array of items.
 * <Item> Array -> Sorted <Item> Array
 *
 * Space Complexity: O(1)
 * O(n): O(n^2)
 * Θ(n): Θ(n^2)
 * Ω(n): Ω(n^2)
 * Exchanges: O(n)
 * Comparisons: n(n-1)/2 (Always)
 *
 * Optimal for: Low memory usage, does very few swaps.
 * Bad for: Always quadratic running time, insensitive to input
**/

public class selectionSort{

    public static <Item extends Comparable<Item>> void sort(Item[] array){
        int length = array.length;
        for(int i = 0; i < length; i++){
            int min = i;
            for(int j = i + 1; j < length; j++)
                if(less(array[j], array[min])) min = j;
            exchange(array, i, min);
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

