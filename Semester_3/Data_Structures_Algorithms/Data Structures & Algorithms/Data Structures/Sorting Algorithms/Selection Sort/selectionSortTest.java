public class selectionSortTest {
    public static void main(String[] args) {
        Integer[] arr = {1, 5, 2, 4, 3};
        selectionSort.sort(arr);
        for (int j : arr) {
            System.out.print(j + " ");
        }
    }
}
