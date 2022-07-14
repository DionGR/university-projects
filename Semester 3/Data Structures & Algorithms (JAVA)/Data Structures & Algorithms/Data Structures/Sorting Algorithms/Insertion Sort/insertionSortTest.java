public class insertionSortTest {

    public static void main(String[] args) {
        Integer[] testArray = {4,2,8,1,3,7,6,5};
        testSort(testArray);
        
        testArray = new Integer[] {4,2,8,1,3,7,6,5};
        testImproved(testArray);
    }
    
    public static void testSort(Integer[] testArray) {
        insertionSort.sort(testArray);
    
        for (Integer integer : testArray) System.out.print(integer + " ");
        System.out.println();
    }
    
    public static void testImproved(Integer[] testArray) {
        insertionSort.sortImproved(testArray);
        
        for (Integer integer : testArray) System.out.print(integer + " ");
        System.out.println();
    }
}
