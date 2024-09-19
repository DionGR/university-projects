import data_structures.queueLinkedList;

import java.util.NoSuchElementException;

/**
 * A Binary Search Tree (BST) is a binary tree where each node has a key
 *
 * @author Dion
 * @content A Binary Search Tree (BST)
 *
 * Space Complexity: O(n)
 * Height dependant! Height is from O(log n) to O(n)
 * Search: O(h)  - Successful Θ(logN) = 1.39logN - Unsuccessful Θ(logn) = 2 + 1.39logN
 * Insert: O(h) - Θ(n)
 * Delete: O(h)
 * Size: O(1)
 *
 * Performance is usually O(log n), similarly to QuickSort as they are parallel, with root being the pivot.
 */

public class BST<Key extends Comparable<Key>, Value extends Comparable<Value>> {
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
    }// A Binary Search Tree Node.
    
    
    /**
     * Get the chosen key's value from the tree.
     *
     * @param Key
     * @return Key's value
     */
    public Value get(Key key){
        return get(root, key);
    }
    
    private Value get(Node node, Key key) {
        if (node == null) return null;
        
        int cmp = key.compareTo(node.key);
        if      (cmp < 0) return get(node.left, key);
        else if (cmp > 0) return get(node.right, key);
        else              return node.value;
    }
    
    public Value getNR(Key key) {
        Node current = root;                              // Start from the root
        while(current != null) {                          // While we haven't reached the end of the tree
            
            int cmp = key.compareTo(current.key);         // Compare the key to the current node's key
            
            if      (cmp < 0) current = current.left;     // If the key is less than the current node's key, go left
            else if (cmp > 0) current = current.right;    // If the key is greater than the current node's key, go right
            else              return current.value;       // If the key is equal to the current node's key, return the value
        }
        return null;                                      // If we've reached the end of the tree, return null
    }
    
    
    /**
     * Put a value in the tree with the given key.
     *
     * @param Key, Value
     */
    public void put(Key key, Value value) {
        root = put(root, key, value);
    }// Put a new node into the tree.
    
    private Node put(Node current, Key key, Value value) {
        if (current == null) return new Node(key, value);                   // If we've reached the end of the tree (a leaf), create a new node
        
        int cmp = key.compareTo(current.key);                               // Compare the key to the current node's key
        
        if      (cmp < 0) current.left = put(current.left, key, value);     // If the key is less than the current node's key, go left
        else if (cmp > 0) current.right = put(current.right, key, value);   // If the key is greater than the current node's key, go right
        else              current.value = value;                            // If the key already exists, update the value
        
        current.count = 1 + size(current.left) + size(current.right);       // Update the current node's count
        
        return current;                                                     // Return the node
    }// HELPER - Put a new node into the tree recursively.
    
    
    /**
     * Put a value in the root of the tree with the given key.
     *
     * @param Key, Value
     */
    public void putRoot(Key key, Value value) {
        root = putRoot(root, key, value);                   // Start from the root
    }// Put a new node into the root of the tree.
    
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
    
    
    /**
     * Delete the given key's value from the tree.
     *
     * @param Key
     */
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
    
    
    /**
     * Merges two trees together.
     *
     * @param BST
     */
    public void merge(BST<Key, Value> other) {
        root = merge(root, other.root);
    }// Merge two trees together.
    
    private Node merge(Node first, Node second) {
        if (first == null) return second;               // If the first tree is empty, return the second tree
        if (second == null) return first;               // If the second tree is empty, return the first tree
        
        if (first.count > second.count) {               // If the first tree's count is greater than the second tree's count
            first.right = merge(first.right, second);   // Recursively merge the right subtree
            first = rotateLeft(first);                  // Rotate the node to the left
            return first;
        }else {                                         // If the second tree's count is greater than the first tree's count
            second.left = merge(first, second.left);    // Recursively merge the left subtree
            second = rotateRight(second);               // Rotate the node to the right
            return second;
        }
    }// HELPER - Merge two trees together recursively.
    
    
    /**
     * Delete the minimum or maximum key from the tree.
     */
    public void deleteMin() {
        root = deleteMin(root);
    }// Delete the minimum key from the tree.
    
    private Node deleteMin(Node current) {
        if (current.left == null) return current.right;                 // If the left subtree is empty, return the right subtree
        
        current.left = deleteMin(current.left);                         // Recursively delete the minimum key from the left subtree
        
        current.count = 1 + size(current.left) + size(current.right);   // Update the current node's count
        
        return current;                                                 // Return the current node
    }// HELPER - Delete the minimum key from the tree recursively.
    
    public void deleteMax() {
        root = deleteMax(root);
    }// Delete the minimum key from the tree.
    
    private Node deleteMax(Node current) {
        if (current.right == null) return current.left;                 // If the left subtree is empty, return the left subtree
        
        current.right = deleteMin(current.right);                       // Recursively delete the minimum key from the right subtree
        
        current.count = 1 + size(current.right) + size(current.left);   // Update the current node's count
        
        return current;                                                 // Return the current node
    }// HELPER - Delete the minimum key from the tree recursively.
    
    
    /**
     * Get the min/max key's value from the tree.
     *
     * @return Max/Min Key's value
     */
    public Key min(){
        if (isEmpty()) throw new NoSuchElementException("The tree is empty.");
        
        Node min = min(root);
        
        return min.key;
    }
    
    private Node min(Node current){
        if (current.left == null) return current;
        
        return min(current.left);
    }
    
    public Key max(){
        if (isEmpty()) throw new NoSuchElementException("The tree is empty.");
        
        Node max = max(root);
        
        return max.key;
    }
    
    private Node max(Node current){
        if (current.right == null) return current;
        
        return max(current.right);
    }
    
    
    public Key minNR(){
        Node current = root;
        
        while (current.left != null) current = current.left;
        
        return current.key;
    }// Non recursive implementation.
    
    public Key maxNR(){
        Node current = root;
        
        while (current.right != null) current = current.right;
        
        return current.key;
    }// Non recursive implementation.
    
    
    /**
     * Get the k-th smallest element from the tree.
     *
     * @param K-th smallest element
     * @return Element's value
     */
    public Value select(int element) {
        return select(root, element);
    }// Select the k-th smallest key.
    
    private Value select(Node current, int element) {
        if (current == null) return null;                                                                     // If we've reached the end of the tree, return null
        
        int subtree_count = (current.left == null) ? 0 : current.left.count;                                   // If the left child is null, the left subtree has 0 nodes
        
        if      (subtree_count > element) return select(current.left,  element);                              // If the left subtree has more nodes than k, go left
        else if (subtree_count < element) return select(current.right, element - subtree_count - 1);   // If the left subtree has less nodes than k, go right
        else                              return current.value;                                               // If the left subtree has exactly k nodes, return the value
    }// HELPER - Select the k-th smallest key.
    
    
    /**
     * Get the largest/smallest key less/more than or equal to the given Key
     *
     * @param Key
     * @return Key
     */
    public Key floor(Key key) {
        Node floor = floor(root, key);                                                // Find the node that is the largest key that is less than or equal to the given key
        if (floor == null) throw new NoSuchElementException("The tree is empty.");    // If the root is null, return null
        return floor.key;                                                             // Otherwise, return the key
    }// Finds the largest key less than or equal to the given key.
    
    private Node floor(Node current, Key key) {
        if (current == null) return null;                 // If we've reached the end of the tree, return null
        
        int cmp = key.compareTo(current.key);             // Compare the key to the current node's key
        
        if (cmp == 0) return current;                     // If the key is equal to the current node's key, return the node
        if (cmp < 0) return floor(current.left, key);     // If the key is less than the current node's key, go left
        
        Node t = floor(current.right, key);               // If the key is greater than the current node's key, go right
        if (t != null) return t;                          // If we've found a node, return it
        else return current;                              // Otherwise, return the current node
    }// HELPER - Finds the largest key less than or equal to the given key.
    
    
    /**
     * Get number of keys less than the given key.
     *
     * @param Key
     * @return int amount of keys less than the given key
     */
    public int rank(Key key) {
        return rank(key, root);
    }// Finds the number of keys less than the given key.
    
    private int rank(Key key, Node current) {
        if (current == null) return 0;                                              // If we've reached the end of the tree, return 0
        
        int cmp = key.compareTo(current.key);                                       // Compare the key to the current node's key
        
        if      (cmp < 0) return rank(key, current.left);                           // If the key is less than the current node's key, go left
        else if (cmp > 0) return 1 + size(current.left) + rank(key, current.right); // If the key is greater than the current node's key, go right
        else              return size(current.left);                                // If the key is equal to the current node's key, return the node's rank
    }// HELPER - Finds the number of keys less than the given key recursively.
    
    
    /**
     * Partition the tree so the k-th element becomes the root.
     *
     * @param k-th element
     */
    public void partition(int k){
        root = partition(root, k);
    }// Partition the tree so the k-th element becomes the root.
    
    private Node partition(Node current, int k) {
        int left_size = (current.left == null) ? 0 : current.left.count;      // If the left child is null, the left subtree has 0 nodes
        
        if (left_size > k) {                                                  // If the left subtree has more nodes than k, go left
            current.left = partition(current.left, k);                        // Partition the left subtree
            current = rotateRight(current);                                   // Rotate the current node to the right
        }
        if(left_size < k) {                                                   // If the left subtree has less nodes than k, go right
            current.right = partition(current.right, k - left_size - 1);    // Partition the right subtree
            current = rotateLeft(current);                                    // Rotate the current node to the left
        }
        
        return current;                                                      // Return the current node
    }// HELPER - Partition the tree so the k-th element becomes the root.
    
    
    /**
     * Print the tree inorder.
     */
    public void print() {
        queueLinkedList<Key> queue = keys();
        while (!queue.isEmpty()) {
            Key item = queue.dequeue();
            if (item == root.key) System.out.print("(" + item + ") ");
            else System.out.print(item + " ");
        }
        System.out.println("\n");
    }// Prints the tree inorder.
    
    
    /**
     * Return the number of nodes in the tree.
     *
     * @param int number of nodes
     */
    public int size() {
        return size(root);
    }// Returns the number of nodes in the tree.
    
    private int size(Node current) {
        if (current == null) return 0;
        return current.count;
    }// HELPER - Returns the number of subnodes in a node.
    
    public boolean isEmpty(){
        return size(root) == 0;
    }
    
    
    /**
     * Returns a queue of all the keys in the tree.
     *
     * @param queueLinkedList of keys
     */
    public queueLinkedList<Key> keys() {
        queueLinkedList<Key> queue = new queueLinkedList<>();
        inorder(root, queue);
        return queue;
    }// Returns a queue of all the keys in the tree.
    
    private void inorder(Node current, queueLinkedList<Key> queue) {
        if (current == null) return;      // If we've reached the end of the tree, return
        inorder(current.left, queue);     // Go left
        queue.enqueue(current.key);       // Add the key to the queue
        inorder(current.right, queue);    // Go right
    }// HELPER - Returns a queue of all the keys in the tree recursively.
}
