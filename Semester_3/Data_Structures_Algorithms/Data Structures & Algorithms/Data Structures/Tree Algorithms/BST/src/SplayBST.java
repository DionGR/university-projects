/**
 * A Splay Binary Search Tree
 *
 * @author Dion
 * @content A Splay Binary Search Tree (BST)
 *
 * Space Complexity: O(n)
 * Height dependant! Height is from O(log n) to O(n)
 * Search: O(h)
 * Insert: O(h)
 * Delete: O(h)
 * Size: O(1)
 *
 * Difference from BST:
 * - Self-balancing at each operation
 * - Each insertion makes the path shorter
 * - Some nodes are closer to the root than others, usually the ones near the one we inserted
 * - Good KEY locality
 * - Improved OVERALL time, not per operation. Slower per operation.
 */

public class SplayBST<Key extends Comparable<Key>, Value extends Comparable<Value>> {
    public Node root;
    
    public class Node {
        private final Key key;
        private Value value;
        private Node left, right;
        private int count;
        
        public Node(Key key, Value value) {
            this.key = key;
            this.value = value;
            this.count = 1;
        }
    }
    
    void put(Key key, Value value) {
        root = putSplay(root, key, value);
    }
    
    private Node putSplay(Node current, Key key, Value value) {
        if (current == null) return new Node(key, value);
        
        int cmp = key.compareTo(current.key);
        
        if (cmp < 0) {
            if (current.left == null) {
                current.left = new Node(key, value);
                return rotateRight(current);
            }
            if (key.compareTo(current.left.key) < 0) {
                current.left.left = putSplay(current.left.left, key, value);
                current = rotateRight(current);
            } else {
                current.left.right = putSplay(current.left.right, key, value);
                current.left = rotateLeft(current.left);
            }
            return rotateRight(current);
        }else {
            if (current.right == null) {
                current.right = new Node(key, value);
                return rotateLeft(current);
            }
            if (key.compareTo(current.right.key) > 0) {
                current.right.right = putSplay(current.right.right, key, value);
                current = rotateLeft(current);
            } else {
                current.right.left = putSplay(current.right.left, key, value);
                current.right = rotateRight(current.right);
            }
            return rotateLeft(current);
        }
    }
    
    
    /**
     * HELPERS - IDENTICAL TO BST
     */
    
    /** INSERTION **/

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
