package data_structures.interfaces;

import java.io.PrintStream;
import data_structures.WordFreq;

public interface WordCounter {
    
    /**
     * Inserts a word into the tree,
     * increments the frequency of the word if it already exists
     *
     * @param w word to be inserted
     */
    void insert(String w);
    
    /**
     *  Searches the tree for the word and returns the frequency of the word,
     *  if the word is not found, returns null
     *
     * @param w word to be inserted
     * @return frequency of the word
     */
    WordFreq search(String w);
    
    /**
     * Removes the word from the tree,
     * if the word is not found, returns null
     *
     * @param w word to be inserted
     */
    void remove(String w);
    
    /**
     * Loads the tree from a file according to specification
     *
     * @param filename name of the file to be loaded
     */
    void load(String filename);
    
    /**
     * Adds the word to the stop word list in O(1)
     *
     * @param w word to be inserted
     */
    void addStopWord(String w);
    
    /**
     * Removes the word from the stop word list
     *
     * @param w word to be inserted
     */
    void removeStopWord(String w);
    
    /**
     * Returns the number of words in the tree
     *
     * @return number of words in the tree
     */
    int getTotalWords();
    
    /**
     * Returns the number of distinct words in the tree
     *
     * @return number of unique words in the tree
     */
    int getDistinctWords();
    
    /**
     *  Returns the frequency of the word,
     *  if the word is not found, returns null
     *
     * @param w word to be inserted
     * @return frequency of the word
     */
    int getFrequency(String w);
    
    /**
     * Returns the word with the highest frequency
     * by traversing the tree and finding the max
     *
     * @return word with the highest frequency
     */
    WordFreq getMaximumFrequency();
    
    /**
     * Returns the mean frequency of the words in the tree
     *
     * @return mean frequency of the words in the tree
     */
    double getMeanFrequency();
    
    /**
     * Prints the tree in alphabetical order,
     * using inorder traversal.
     *
     * @param stream PrintStream to print to
     */
    void printTreeAlphabetically(PrintStream stream);
    
    /**
     * Prints the tree by frequency
     *
     * @param stream PrintStream to print to
     */
    void printTreeByFrequency(PrintStream stream);
}
