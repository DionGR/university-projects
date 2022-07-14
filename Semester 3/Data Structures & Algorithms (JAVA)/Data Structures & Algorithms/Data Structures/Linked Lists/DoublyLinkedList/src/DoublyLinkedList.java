public class DoublyLinkedList<T>{
    
    private Node<T> head;
    private Node<T> tail;
    private int size;
    
    public DoublyLinkedList(){
        this.head = new Node<>(null);
        this.tail = new Node<>(null);
        this.size = 0;
    }
    
    public void push(T data){
        Node<T> new_node = new Node<T>(data);
        
        if (head.next == null){
            head.next = new_node;
            tail.prev = new_node;
            new_node.prev = head;
            new_node.next = tail;
        } else {
            new_node.next = head.next;
            head.next.prev = new_node;
            new_node.prev = head;
            head.next = new_node;
        }
        
        ++size;
    }
    
    public void push(Node<T> node){
        if (node == null) throw new NullPointerException();
        
        if (head.next == null){
            head.next = node;
            tail.prev = node;
            node.prev = head;
            node.next = tail;
        } else {
            node.next = head.next;
            head.next.prev = node;
            node.prev = head;
            head.next = node;
        }
        
        ++size;
    }
    
    public void reinsert(Node<T> node) {
        if (node == null) throw new NullPointerException();
        
        removeNode(node);
        push(node.data);
    }
    
    public void removeNode(Node<T> node) {
        if (node == null) throw new NullPointerException();
        
        if (node.prev != null)
            node.prev.next = node.next;
        if (node.next != null)
            node.next.prev = node.prev;
        
        --size;
    }
    
    public T pop(){
        if (head.next == null) throw new NullPointerException();
        
        Node<T> popped = head.next;
        
        head.next = popped.next;
        if (popped.next != null)
            popped.next.prev = head;
        
        --size;
        return popped.data;
    }
    
    public T popLast() {
        if (head.next == null) throw new NullPointerException();
        
        Node<T> popped = tail.prev;
        
        tail.prev = popped.prev;
        popped.prev.next = tail;
        
        --size;
        
        return popped.data;
    }
    
    public int size() {
        return size;
    }
}
