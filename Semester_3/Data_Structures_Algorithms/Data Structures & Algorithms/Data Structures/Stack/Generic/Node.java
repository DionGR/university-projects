class Node<T>{
    Item data;
    Node<T> next;

    public Node(T data, Node<T> next){
        this.data = data;
        this.next = next;
    }
}