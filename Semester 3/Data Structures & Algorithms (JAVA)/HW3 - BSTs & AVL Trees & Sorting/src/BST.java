import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintStream;

import data_structures.LinkedList;
import data_structures.WordFreq;
import data_structures.interfaces.WordCounter;
import data_structures.sorting.mergeSort;

/**
 * An AVL Binary Search Tree (BST) implementation.
 *
 * @authors: P3200262
 * @info: Made for the course of Data Structures @ AUEB 2021-2022
 *
 * Overriden method explanations will be found in the WordCounter class interface!
 *
 * Space Complexity: O(n)
 * Height is log(n) because of the AVL property.
 * Search: O(h)
 * Insert: O(h)
 * Delete: O(h)
 * Size: O(1)
 *
 * Difference from basic BST:
 *  - Always balance after insert
 *  - Each operation has a O(1) rebalancing action in it
 *  - Height is always O(log n)
 */


public class BST implements WordCounter {
    private TreeNode root;
    private LinkedList<String> stopwords;
    
    private class TreeNode implements Comparable<TreeNode> {
        private final WordFreq item;
        private TreeNode left, right;
        private int subTreeSize;
        
        public TreeNode(WordFreq item) {
            this.item = item;
            this.subTreeSize = 1;
        }
        
        public int compareTo(TreeNode other) {
            return item.compareTo(other.item);
        }
    }// A Binary Search Tree Node.
    
    public BST() {
        this.root = null;
        this.stopwords = new LinkedList<>();
    }
    
    
    @Override
    public void insert(String w) {
        root = insert(root, w.toLowerCase());
    }
    
    private TreeNode insert(TreeNode current, String key) {
        if (current == null) return new TreeNode(new WordFreq(key));      // If we've reached the end of the tree (a leaf), create a new node
        
        int cmp = key.compareTo(current.item.key());                      // Compare the key to the current node's key
        
        if      (cmp < 0) current.left = insert(current.left, key);       // If the key is less than the current node's key, go left
        else if (cmp > 0) current.right = insert(current.right, key);     // If the key is greater than the current node's key, go right
        else              current.item.incrementFreq();                   // If the key already exists, increment the frequency
        
        current.subTreeSize = 1 + size(current.left) + size(current.right);// Update the current node's count
        current = balance(current);                                       // Balance the tree
        return current;                                                   // Return the node
    }// HELPER - Put a new node into the tree recursively.
    
    private TreeNode insertRoot(TreeNode current, WordFreq item) {
        if (current == null) return new TreeNode(item);                // If we've reached the end of the tree (a leaf), create a new node
        
        if (item.key().compareTo(current.item.key()) < 0) {            // If the key is less than the current node's key, go left
            current.left = insertRoot(current.left, item);             // Recursively call insertRoot on the left child
            current = rotateRight(current);                            // Rotate the node to the right
        }else {                                                        // If the key is greater than the current node's key, go right
            current.right = insertRoot(current.right, item);           // Recursively call insertRoot on the right child
            current = rotateLeft(current);                             // Rotate the node to the left
        }
        
        current.subTreeSize = 1 + size(current.left) + size(current.right);
        current = balance(current);                                       // Balance the tree
        return current;
    }// HELPER - Put a new node into the root of the tree recursively.
    
    @Override
    public void remove(String w) {
        root = remove(root, w.toLowerCase());
    }
    
    private TreeNode remove(TreeNode current, String key) {
        if (current == null) return null;                                   // If we've reached the end of the tree (a leaf), return null
        
        int cmp = key.compareTo(current.item.key());
                                                                            // Compare the key to the current node's key
        if (cmp < 0)  current.left = remove(current.left, key);             // If the key is less than the current node's key, go left
        if (cmp > 0)  current.right = remove(current.right, key);           // If the key is greater than the current node's key, go right
        if (cmp == 0) current = mergeLR(current.left, current.right);       // If the key is equal to the current node's key, merge the left and right subtrees
        
        if (current != null) current.subTreeSize = 1 + size(current.left) + size(current.right);
        current = balance(current);                                         // Balance the tree
        return current;                                                     // Return the node
    }// HELPER - Delete a key from the tree recursively.
    
    /**
     * Merges two trees together.
     *
     * @return a merged tree
     */
    private TreeNode mergeLR(TreeNode first, TreeNode second) {
        if (second == null) return first;
        second = partition(second, 0);
        second.left = first;
        second.subTreeSize = 1 + size(second.left) + size(second.right);
        return second;
    }// HELPER - Merge two trees together recursively.
    
    private TreeNode partition(TreeNode current, int k) {
        int left_size = (current.left == null) ? 0 : current.left.subTreeSize;      // If the left child is null, the left subtree has 0 nodes
        
        if (left_size > k) {                                                        // If the left subtree has more nodes than k, go left
            current.left = partition(current.left, k);                              // Partition the left subtree
            current = rotateRight(current);                                         // Rotate the current node to the right
        }
        if(left_size < k) {                                                         // If the left subtree has less nodes than k, go right
            current.right = partition(current.right, k - left_size - 1);          // Partition the right subtree
            current = rotateLeft(current);                                          // Rotate the current node to the left
        }
        
        return current;                                                             // Return the current node
    }// HELPER - Partition the tree so the k-th element becomes the root.
    
    /**
     * Rotate a tree to the right/left
     *
     * @param current the tree to rotate
     * @return the rotated tree
     */
    private TreeNode rotateRight(TreeNode current) {
        TreeNode left_child = current.left;                                           // Save the left child
        current.left = left_child.right;                                              // Make the left child the new right child
        left_child.right = current;                                                   // Make the current node the new left child
        
        current.subTreeSize = 1 + size(current.left) + size(current.right);               // Update the current node's count
        left_child.subTreeSize = 1 + size(left_child.left) + size(left_child.right);      // Update the left child's count
        
        return left_child;                                                            // Return the left child
    }// HELPER - Rotate the node to the right.
    
    private TreeNode rotateLeft(TreeNode current) {
        TreeNode right_child = current.right;
        current.right = right_child.left;
        right_child.left = current;
        
        current.subTreeSize = 1 + size(current.left) + size(current.right);
        right_child.subTreeSize = 1 + size(right_child.left) + size(right_child.right);
        
        return right_child;
    }// HELPER - Rotate the node to the left.
    
    /** Balance the tree.
     *
     * @param node the node to balance
     * @return the balanced tree
     */
    private TreeNode balance(TreeNode node) {
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
    
    @Override
    public WordFreq search(String w) {
        return search(root, w.toLowerCase());
    }
    
    private WordFreq search(TreeNode current, String key) {
        if (current == null) return null;                       // If we've reached the end of the tree (a leaf), return null
        
        int cmp = key.compareTo(current.item.key());            // Compare the key to the current node's key
        if      (cmp < 0) return search(current.left, key);     // If the key is less than the current node's key, go left
        else if (cmp > 0) return search(current.right, key);    // If the key is greater than the current node's key, go right
        else {                                                  // If the key is equal to the current node's key, return the node
            if(current.item.getFreq() > getMeanFrequency()){    // If the frequency is greater than the mean frequency, re-insert it at the root
                remove(current.item.key());
                root = insertRoot(root, current.item);
            }
            return current.item;
        }
    }// HELPER - Search for a key in the tree recursively.
    
    @Override
    public void load(String filename) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));    // Create a new buffer
            String line;
            
            if(br.ready()) {                                                    // If the file is not empty
                Matcher open;
                Pattern word = Pattern.compile("[a-zA-Z]+'?[a-zA-Z]*");         // Create a pattern to match words
    
                while ((line = br.readLine()) != null) {                        // While there are still lines to read
                    open = word.matcher(line);                                  // Create a matcher to match words
    
                    while (open.find()) {                                       // While there are still words to match
                        String word_to_add = open.group().toLowerCase();        // Get the word to add
                        if (!stopwords.search(word_to_add)) insert(word_to_add);// If the word is not a stopword, add it to the tree
                    }
                }
            }
            br.close();                                                        // Close the buffer
        }   catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("Error loading file");
        }
    }
    
    public int size() {
        return size(root);
    }// Returns the number of nodes in the tree.
    
    private int size(TreeNode current) {
        if (current == null) return 0;
        return current.subTreeSize;
    }// HELPER - Returns the number of subnodes in a node.
    
    @Override
    public int getTotalWords() {
        if (root == null) return 0;
        
        LinkedList<TreeNode> stack = new LinkedList<>();
        int total = 0;
        
        stack.insert(root);

        while (!stack.isEmpty()) {
            TreeNode current = stack.pop();
            total += current.item.getFreq();
            if (current.right != null) stack.insert(current.right);
            if (current.left != null) stack.insert(current.left);
        }
        
        return total;
    }
    
    @Override
    public int getDistinctWords() {
        return size();
    }
    
    @Override
    public int getFrequency(String w) {
        WordFreq item = search(root, w);
        if (item == null) return 0;
        return item.getFreq();
    }
    
    /**
     * Iterative traversals using stack pointer will be used from now on
     * The absence of comments is intentional as everything was show in the slides
     */
    
    @Override
    public double getMeanFrequency() {
        if (root == null) return 0;
        
        LinkedList<TreeNode> stack = new LinkedList<>();
        int sum = 0;
        
        stack.insert(root);
        while (!stack.isEmpty()) {
            TreeNode current = stack.pop();
            sum += current.item.getFreq();
            if (current.right != null) stack.insert(current.right);
            if (current.left != null) stack.insert(current.left);
        }
        
        return (double)sum/getDistinctWords();
    }
    
    @Override
    public WordFreq getMaximumFrequency() {
        if (root == null) return null;
        
        LinkedList<TreeNode> stack = new LinkedList<>();
        
        stack.insert(root);
        WordFreq max = root.item;
        
        while (!stack.isEmpty()) {
            TreeNode current = stack.pop();
            if (current.item.getFreq() > max.getFreq())
                max = current.item;                                             // If the current node's frequency is greater than the max, update the max
    
            if (current.right != null) stack.insert(current.right);
            if (current.left != null) stack.insert(current.left);
        }
        return max;
    }
    
    @Override
    public void addStopWord(String w) {
        stopwords.insert(w.toLowerCase());
    }
    
    @Override
    public void removeStopWord(String w) {
        stopwords.remove(w.toLowerCase());
    }
    
    @Override
    public void printTreeAlphabetically(PrintStream stream) {
        if (root == null) return;
        
        LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode current = root;
        
        while (!stack.isEmpty() || current != null) {
            if (current != null) {
                stack.insert(current);
                current = current.left;
            }else{
                current = stack.pop();
                stream.println(current.item.key());
                current = current.right;
            }
        }
    }
    
    @Override
    public void printTreeByFrequency(PrintStream stream) {
        if (root == null) return;
        
        /* Create an array to store WordFreq objects */
        WordFreq[] array = new WordFreq[size()];
        int index = 0;
        
        /* Create a stack to store the nodes */
        LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode current = root;
    
        /* Transfer the node data into the array */
        while (!stack.isEmpty() || current != null) {
            if (current != null) {
                stack.insert(current);
                current = current.left;
            }else{
                current = stack.pop();
                array[index++] = current.item;
                current = current.right;
            }
        }

        /* Sort the array and print it */
        mergeSort.sort(array);
        for (WordFreq wordFreq: array)
            stream.println(wordFreq.key() + ": " + wordFreq.getFreq());
    }
    
    /* Main method for unit testing */
    public static void main(String[] args) {
        BST bst = new BST();
        
        bst.addStopWord("the");         // Most common word in Macbeth
        
        if(args.length != 0)
            bst.load(args[0]);
        else
            bst.load("testfile.txt");
        
        /* Search Testing */
        System.out.println("The frequency: " + bst.search("the"));
        System.out.println("You frequency: " + bst.search("you"));
        System.out.println("Love frequency: " + bst.search("love"));
        
        System.out.println();
        
        /* Removal testing */
        System.out.println("Total words before removal: " + bst.getTotalWords());
        System.out.println("Size before removal: " + bst.size());
        bst.remove("love");
        bst.remove("you");
        System.out.println("Total words after removal: " + bst.getTotalWords());
        System.out.println("Size after removal: " + bst.size());
        
        System.out.println();
        
        /* Total and Distinct Word Count Testing */
        System.out.println(bst.getTotalWords());
        System.out.println(bst.getDistinctWords());
        
        System.out.println("");
        
        /* Max and Mean Frequency Testing */
        System.out.println(bst.getMeanFrequency());
        System.out.println(bst.getMaximumFrequency());
        
        System.out.println("");
        
        /* Print BST Testing */
        bst.printTreeAlphabetically(new PrintStream(System.out));
        System.out.println("\n");
        bst.printTreeByFrequency(new PrintStream(System.out));
    }
}

