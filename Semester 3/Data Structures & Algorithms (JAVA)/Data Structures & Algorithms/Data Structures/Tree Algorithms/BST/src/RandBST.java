import java.util.Random;

/**
 * A Randomized Binary Search Tree
 *
 * @author Dion
 * @content A Randomized Binary Search Tree (BST)
 *
 * Space Complexity: O(n)
 * Height dependant! Height is from O(log n) to O(n)
 * Search: O(h) - Θ(2lnN)
 * Insert: O(h)
 * Delete: O(h)
 * Size: O(1)
 *
 * Difference from BST:
 * - Insertion happens randomly at root or in a leaf (Probability 1/(N+1) for root)
 */

public class RandBST<Key extends Comparable<Key>, Value extends Comparable<Value>> {
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
    
    /**
     * Insert a node at the root with probability 1/(N+1),
     * else insert a node at a leaf.
     *
     * @param key, value
     */
    public void put(Key key, Value value) {
        root = put(root, key, value);
    }// Put a new node into the tree.
    
    private Node put(Node current, Key key, Value value) {
        if (current == null) return new Node(key, value);               // If we've reached the end of the tree (a leaf), create a new node
        
        if (Math.random()*(current.count + 1) < 1.0)                    // With probability 1/(N+1), insert the node at the root
            return putRoot(current, key, value);                        // Else insert the node at a leaf
        
        if (key.compareTo(current.key) < 0)                             // If the key is less than the current node's key, go left
            current.left = putRecursive(current.left, key, value);
        else                                                            // If the key is greater than the current node's key, go right
            current.right = putRecursive(current.right, key, value);
        
        return current;
    }
    
    /**
     * Random merging
     *
     * @param key, value
     */
    private Node merge(Node first, Node second) {
        int total_size = size(first) + size(second);
        
        if (first == null) return second;               // If the first tree is empty, return the second tree
        if (second == null) return first;               // If the second tree is empty, return the first tree
        
        
        if (Math.random() * total_size < 1.0f* first.count) {               // If the first tree's count is greater than the second tree's count
            first.right = merge(first.right, second);   // Recursively merge the right subtree
            return first;
        }else {                                         // If the second tree's count is greater than the first tree's count
            second.left = merge(first, second.left);    // Recursively merge the left subtree
            return second;
        }
    }// HELPER - Merge two trees together recursively.
    
    
    /**
     * HELPERS - IDENTICAL TO BST
     */
    
    /** INSERTION **/
    private Node putRecursive(Node current, Key key, Value value) {
        if (current == null) return new Node(key, value);                               // If we've reached the end of the tree (a leaf), create a new node
        
        int cmp = key.compareTo(current.key);                                        // Compare the key to the current node's key
        
        if      (cmp < 0) current.left = putRecursive(current.left, key, value);     // If the key is less than the current node's key, go left
        else if (cmp > 0) current.right = putRecursive(current.right, key, value);   // If the key is greater than the current node's key, go right
        else              current.value = value;                                    // If the key already exists, update the value
        current.count = 1 + size(current.left) + size(current.right);                // Update the current node's count
        return current;                                                             // Return the node
    }// HELPER - Put a new node into the tree recursively.
    
    private Node putRoot(Node current, Key key, Value value) {
        if (current == null) return new Node(key, value);       // If we've reached the end of the tree (a leaf), create a new node
        
        if (key.compareTo(current.key) < 0) {                   // If the key is less than the current node's key, go left
            current.left = putRoot(current.left, key, value);   // Recursively call putRoot on the left child
            current = rotateRight(current);                     // Rotate the node to the right
        }else {                                                 // If the key is greater than the current node's key, go right
            current.right = putRoot(current.right, key, value); // Recursively call putRoot on the right child
            current = rotateLeft(current);                      // Rotate the node to the left
        }
        
        return current;
    }// HELPER - Put a new node into the root of the tree recursively.
    
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
    
    /** DELETION **/
    public void delete(Key key) {
        root = delete(root, key);
    }// Delete a key from the tree.
    
    private Node delete(Node current, Key key) {
        if (current == null) return null;                                            // If we've reached the end of the tree (a leaf), return null
        
        Key crnt_k = current.key;                                                    // Save the current node's key
        if (key.compareTo(crnt_k) < 0)  current.left = delete(current.left, key);    // If the key is less than the current node's key, go left
        if (key.compareTo(crnt_k) > 0)  current.right = delete(current.right, key);  // If the key is greater than the current node's key, go right
        if (key.compareTo(crnt_k) == 0) current = merge(current.left, current.right);// If the key is equal to the current node's key, merge the left and right subtrees
        
        return current;                                                              // Return the node
    }// HELPER - Delete a key from the tree recursively.
    
    
    
    /** ETC **/
    private int size(Node current) {
        if (current == null) return 0;
        return current.count;
    }// HELPER - Returns the number of subnodes in a node.
}