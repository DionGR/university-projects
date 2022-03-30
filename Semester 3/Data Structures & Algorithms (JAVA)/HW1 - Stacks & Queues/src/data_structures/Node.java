package data_structures;

/**
 * This is a Generic data_structures.Node implementation. All variables are null-initialized and have
 * to be manually altered as there is no constructor.
 *
 * @authors: P3200262, P3200298
 * @info: Made for the course of Data Structures @ AUEB 2021-2022
 **/


public class Node<T> {
    T data;
    Node<T> next;
    
    public Node(T data, Node<T> next) {
        this.data = data;
        this.next = next;
    }// Constructor - initialize the Node with the given data and next Node.
}
