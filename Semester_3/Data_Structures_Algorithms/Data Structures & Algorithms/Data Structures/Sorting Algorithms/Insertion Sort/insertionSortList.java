public class insertionSortList {
    public static Node insertionSortList(Node head) {
        Node dummy = new Node(0);
        Node prev = dummy;
        
        while (head != null) {
            Node temp = head.next;
        
        /* Before insert, the prev is at the last node of the sorted list.
           Only the last node's value is larger than the current inserting node
           should we move the temp back to the head*/
            if (prev.data >= head.data) prev = dummy;
            
            while (prev.next != null && prev.next.data < head.data) {
                prev = prev.next;
            }
            
            head.next = prev.next;
            prev.next = head;
            // prev = dummy; // Don't set prev to the head of the list after insert
            head = temp;
        }
        return dummy.next;
    }
}
