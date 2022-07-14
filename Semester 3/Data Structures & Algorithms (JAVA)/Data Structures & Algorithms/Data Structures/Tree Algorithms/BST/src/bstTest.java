import data_structures.queueLinkedList;

public class bstTest {
    
    public static void main(String[] args){
        testBST();
    }
    
    public static void testBST(){
        BST<String, Integer> bst = new BST<>();
        bst.put("Dion", 1);
        bst.put("Chrysa", 2);
        bst.put("Kiki", 3);
    
        bst.delete("Dion");
        System.out.println(bst.size());

        
        
        
       
    }
}
