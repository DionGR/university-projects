package data_structures;

/**
 * A class that represents a word and its frequency in a text
 *
 * @authors: P3200262
 * @info: Made for the course of Data Structures @ AUEB 2021-2022
 **/

public class WordFreq implements Comparable<WordFreq> {
    private final String word;
    private int freq;
    
    public WordFreq(String word) {
        this.word = word;
        this.freq = 1;
    }
    
    public String key(){
        return word;
    }
    
    public void incrementFreq() {
        ++freq;
    }
    
    public int getFreq() {
        return freq;
    }
    
    @Override
    public String toString() {
        return word + ": " + freq;
    }
    
    @Override
    public int compareTo(WordFreq other) {
        return Integer.compare(other.freq, this.freq);
    }
}
