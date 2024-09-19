/**
 * A Red-Black Search Tree
 *
 * @author Dion
 * @content A Red-Black Search Tree (BST)
 *
 * Space Complexity: O(n)
 * Height is ALWAYS log(n)
 * Search: O(log(n)) = 2logN+2 - Θ(log(n)) = 1,002logN
 * Insert: O(log(n)) - Θ(log(n)) = 1,002logN
 * Delete: O(log(n)) - Θ(log(n)) = 1,002logN
 * Size: O(1)
 *
 * Difference from BST:
 * - Best guarantees O(log n) search
 * - Is almost always perfectly balanced
 * - Excellent Θ guarantee
 */

public class RedBlackBST<Key extends Comparable<Key>, Value extends Comparable<Value>> {
    public Node root;
    
    public class Node {
        private final Key key;
        private Value value;
        private Node left, right;
        private int count;
        private boolean red = true;
        
        public Node(Key key, Value value) {
            this.key = key;
            this.value = value;
            this.count = 1;
        }
    }
    
    public void put(Key key, Value value) {
        root = put(root, key, value, false);
        root.red = false;
    }
    
    private Node put(Node node, Key key, Value value, boolean swap) {
        if (node == null) return new Node(key, value);                               // If the node is null, create a new node.
        
        if (isRed(node.left) && isRed(node.right))  flipColors(node);                // If the node is 4node, make it black.
        
        
        if (key.compareTo(node.key) < 0) {                                           // If the key is less than the current node's key,
            node.left = put(node.left, key, value, false);                     // Put the key in the left child.
            
            if (isRed(node) && isRed(node.left) && swap) node = rotateRight(node);  // If the node is red and the left child is red, rotate the node to the right.
            
            if (isRed(node.left) && isRed(node.left.left)) {                        // If the left child is red and the left child's left child is red,
                node = rotateRight(node);                                           // Rotate the node to the right.
                node.red = false;                                                   // Make the node black.
                node.right.red = true;                                              // Make the right child red.
            }
        }else{                                                                      // If the key is greater than the current node's key,
            node.right = put(node.right, key, value, true);                    // Put the key in the right child.
            
            if (isRed(node) && isRed(node.right) && !swap) node = rotateLeft(node); // If the node is red and the right child is red, rotate the node to the left.
            
            if (isRed(node.right) && isRed(node.right.right)) {                     // If the right child is red and the right child's right child is red,
                node = rotateLeft(node);                                            // Rotate the node to the left.
                node.red = false;                                                   // Make the node black.
                node.left.red = true;                                       // Make the left child red.
            }
        }
        
        node.count = 1 + size(node.left) + size(node.right);                     // Update the node's count.
        return node;
    }
    
    private boolean isRed(Node x) {
        if (x == null) return false;
        return x.red;
    }// HELPER - Is the node red?
    
    private void flipColors(Node x) {
        x.red = !x.red;
        x.left.red = !x.left.red;
        x.right.red = !x.right.red;
    }// HELPER - Flips the colors of a node and its children.
    
    /**
     * HELPERS - IDENTICAL TO BST
     */
    
    /** ROTATION **/

    private Node rotateRight(Node current) {
        Node left_child = current.left;                                         // Save the left child
        current.left = left_child.right;                                        // Make the left child the new right child
        left_child.right = current;                                             // Make the current node the new left child
    
        current.count = 1 + size(current.left) + size(current.right);           // Update the current node's count
        left_child.count = 1 + size(left_child.left) + size(left_child.right);  // Update the left child's count
    
        return left_child;                                                      // Return the left child
    }// HELPER - Rotate the node to the right.
    
    private Node rotateLeft(Node current) {
        Node right_child = current.right;
        current.right = right_child.left;
        right_child.left = current;
        
        current.count = 1 + size(current.left) + size(current.right);
        right_child.count = 1 + size(right_child.left) + size(right_child.right);
        
        return right_child;
    }// HELPER - Rotate the node to the left.
    
    
    /** ETC **/
    
    private int size(Node current) {
        if (current == null) return 0;
        return current.count;
    }// HELPER - Returns the number of subnodes in a node.
}