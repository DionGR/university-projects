class mergeSortTest {
    
    public static void main(String[] args) {
        TestSortString();
    }
    
    public static void TestSortString() {

        // Reversed alphabet array
        String[] array = {"b", "c", "a", "d", "g", "e"};
        
        mergeSort.sort(array);
    
        for (String s : array) {
            System.out.println(s);
        }
    }
    
}
