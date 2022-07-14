/**
 * An AVL Binary Search Tree
 *
 * @author Dion
 * @content An AVL (BST)
 * Definition: An AVL tree is a binary search tree where the sizes of the two child subtrees of every node differ by at most one.
 *
 * Space Complexity: O(n)
 * Height is ALWAYS log(n)
 * Search: O(log(n))
 * Insert: O(log(n))
 * Delete: O(log(n))
 * Size: O(1)
 *
 * Difference from BST:
 * - Always balance after insert/delete
 * - Each operation has a O(1) rebalancing action in it
 * - Height is ALWAYS O(log n)
 */

public class AVLBST<Key extends Comparable<Key>, Value extends Comparable<Value>> {
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
     * Inserts a key-value pair into the tree and rebalances it
     *
     *  If the tree is unbalanced after one insertion:
     *  1) The issue is in the path of the insertion
     *  2) The issue is at least 2 levels above the inserted node
     */
    
    public void insert(Key key, Value value) {
        root = insert(root, key, value);
    }
    
    private Node insert(Node node, Key key, Value value) {
        if (node == null) {
            return new Node(key, value);
        }
        
        int cmp = key.compareTo(node.key);
        
        if (cmp < 0) {
            node.left = insert(node.left, key, value);
        } else if (cmp > 0) {
            node.right = insert(node.right, key, value);
        } else {
            node.value = value;
        }
        
        node.count = 1 + size(node.left) + size(node.right);
        
        node = balance(node);    // Balance the node ! ONLY DIFFERENCE FROM BST !
        return node;
    }
    
    /**
     * Balances the tree after an insertion
     *
     * @param node The node to balance
     * @return The balanced node
     */
    private Node balance(Node node) {
        if (node == null) return null;                                  // If the node is null, return null
    
        int balance = size(node.left) - size(node.right);               // Calculate the balance of the node
    
        if (balance > 1) {
            if (size(node.left.left) < size(node.left.right))           // If the left child's left subtree has less nodes than the left child's right subtree
                node.left = rotateLeft(node.left);                      // Rotate the left child to the left
            node = rotateRight(node);                                   // Rotate the node to the right
        } else if (balance < -1) {                                      // If the left child's right subtree has less nodes than the left child's left subtree
            if (size(node.right.right) < size(node.right.left))         // If the right child's right subtree has less nodes than the right child's left subtree
                node.right = rotateRight(node.right);                   // Rotate the right child to the right
            node = rotateLeft(node);                                    // Rotate the node to the left
        }
        return node;                                                    // Return the balanced node
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
    
    /** SIZE **/
    private int size(Node current) {
        if (current == null) return 0;
        return current.count;
    }// HELPER - Returns the number of subnodes in a node.
}
