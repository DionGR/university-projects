public class Node<Value>{
    public Value value;
    public Node<Value> next;
    
    public Node(){
        this(null, null);
    }
    
    public Node(Value value, Node<Value> next){
        this.value = value;
        this.next = next;
    }
}
