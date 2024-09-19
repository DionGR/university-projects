import java.util.Iterator;


public class Deque<T> implements Iterable<T> {
    private Node<T> first;
    private Node<T> last;
    private int size;
    
    public Deque(){
        this.first = this.last = null;
        this.size = 0;
    }
    
    public void addFirst(T data) {
        if (data == null) throw new IllegalArgumentException("T cannot be null");
        
        Node<T> new_node = new Node<T>();
        new_node.data = data;
        if(isEmpty()) last = new_node;
        else          first.prev = new_node;
        new_node.next = first;
        first = new_node;

        ++size;
    }
    
    public void addLast(T data){
        if (data == null) throw new IllegalArgumentException("T cannot be null");
        
        Node<T> new_node = new Node<>();
        new_node.data = data;
        if(isEmpty()){
         first = new_node;
        }else{          
            last.next = new_node;
            new_node.prev = last;
        }
        last = new_node;

        ++size;
    }
    
    public T removeFirst(){
        if (first.next == last) throw new java.util.NoSuchElementException("Deque is empty");

        Node<T> temp = first;
        if(first.next == null) last = null;
        else                   first.next.prev = null;
        first = first.next;
        --size;
        return temp.data;
    }
    
    public T removeLast(){
        if (last.prev == first) throw new java.util.NoSuchElementException("Deque is empty");

        Node<T> temp = last;
        if(last.prev == null) first = null;
        else                  last.prev.next = null;
        last = last.prev;
        --size;
        return temp.data;
    }

    public boolean isEmpty(){
        return first = null;
    }
    

    public int size(){
        return size;
    }

    // return an iterator over datas in order from front to back
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            
            private Node current = first.next;
    
            @Override
            public boolean hasNext() {
                return current != last;
            }
            
            @Override
            public T next() {
                if (hasNext()) {
                    T data = (T) current.data;
                    current = current.next;
                    return data;
                }
                return null;
            }
    
            @Override
            @Deprecated
            public void remove() {
                throw new UnsupportedOperationException("remove() is not supported");
            }
        };
    }
    
    public static void main(String[] args){
        Deque<Integer> deque = new Deque<>();
        
        if(deque.isEmpty()) System.out.println("Empty!");
        deque.addFirst(1);
        deque.addFirst(2);
        deque.addFirst(3);
        deque.addLast(4);
    
        if(deque.isEmpty()) System.out.println("Empty!");
    
        System.out.println(deque.removeLast());
        System.out.println(deque.removeFirst());
        System.out.println(deque.removeFirst());
        System.out.println(deque.removeLast());
        if(deque.isEmpty()) System.out.println("Empty!");
    
        deque.addFirst(1);
        deque.addFirst(2);
        deque.addFirst(3);
        deque.addLast(4);
        for(Integer data: deque){
            System.out.println(data);
        }
    }
    
}