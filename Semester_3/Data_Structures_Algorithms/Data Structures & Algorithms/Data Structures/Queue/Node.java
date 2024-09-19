public class Node<T>{
    T data;
    Node<T> next;
    
    public Node(){
        this(null, null);
    }// Default constructor - null initialize the data_structures.Node if no arguments are given.
    
    public Node(T data, Node<T> next){
        this.data = data;
        this.next = next;
    }// Constructor - initialize the data_structures.Node with the given data and next data_structures.Node.
}