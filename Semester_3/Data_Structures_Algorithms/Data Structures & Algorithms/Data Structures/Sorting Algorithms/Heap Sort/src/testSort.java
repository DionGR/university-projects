import java.io.PrintStream;

public class testSort {
    public static void main(String[] args) {
        testSort(new String[]{"S", "O", "R", "T", "E", "X", "A", "M", "P", "L", "E"});
        testSort(new Integer[]{5, 4, 3, 2, 1});
    }
    
    public static void testSort(Comparable[] a) {
        PrintStream StdOut = new PrintStream(System.out, true);
        StdOut.println("Unsorted array: ");
        for (Comparable comparable : a) {
            StdOut.print(comparable + " ");
        }
        StdOut.println();
        StdOut.println("Sorted array: ");
        heapSort.sort(a);
        
        for (int i = 0; i < a.length; i++) {
            StdOut.print(a[i] + " ");
        }
        StdOut.println();
    }
}
