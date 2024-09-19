import java.util.NoSuchElementException;

public class SinglyLinkedList<Value>{
    private final Node<Value> head;
    private int size;
    
    public SinglyLinkedList(){
        this.head = new Node<>();
        this.size = 0;
    }
    
    public void insert(Value value){
        head.next = new Node<>(value, head.next);
        ++size;
    }
    
    public Value get(int index){
        if (index > size) throw new IndexOutOfBoundsException("The index is out of bounds!");
        
        Node<Value> current = head.next;
        while(index > 0){
            --index;
            current = current.next;
        }
        
        return current.value;
    }
    
    public void remove(Value value){
        if (head.next == null) throw new NoSuchElementException("The list is empty!");
        
        Node<Value> prev = head;
        
        for (Node<Value> current = head.next; current.next != null; current = current.next){
            if (current.value == value){
                prev.next = current.next;
                --size;
                return;
            }
            prev = current;
        }
    }
    
    public Value pop(){
        if (head.next == null) throw new NoSuchElementException("The list is empty!");
        
        Value to_pop = head.next.value;
        head.next = head.next.next;
        --size;
        return to_pop;
    }
    
    public int size(){
        return size;
    }
}
