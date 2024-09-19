package data_structures;

public class Node<T> {
    public T data;
    public Node<T> next, prev;
    
    public Node(T data) {
        this.data = data;
    }
}
