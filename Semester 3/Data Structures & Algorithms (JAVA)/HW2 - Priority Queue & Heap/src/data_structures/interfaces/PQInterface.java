package data_structures.interfaces;

/**
 * Interface for a priority queue.
 *
 * @authors: Evangelos Markakis, P3200262, P3200298
 * @info: Made for the course of Data Structures @ AUEB 2021-2022
 **/

public interface PQInterface<T> {
    boolean isEmpty();   // check if the queue is empty
    int size();          // return the number of active elements in the queue
    void insert(T x);    // insert the object x to the queue
    T max();             // return without removing the object with the highest priority
    T getMax();          // remove and return the object with the highest priority
}