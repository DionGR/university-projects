package components.software;

/**
 * PART A: A Task record that holds values.
 *
 * Is only used to represent a task and its attributes (ID, time).
 *
 * @authors: P3200262, P3200298
 * @info: Made for the course of Data Structures @ AUEB 2021-2022
 **/


public class Task implements Comparable<Task> {
    private final int ID;
    private final int time;
    
    public Task(int ID, int time) {
        this.ID = ID;
        this.time = time;
    }
    
    public int getID() {
        return ID;
    }

    public int getTime() {
        return time;
    }
    
    @Override
    public String toString() {
        return  "[ID: " + ID + " | Time: " + time + "]";
    }// Returns the task's ID and time.

    @Override
    public int compareTo(Task other) {
        int difference = this.time - other.getTime();
        if (difference == 0) return other.getID() - this.ID;
        return difference;
    }// Compares the time of two tasks. If they are equal, compares their IDs.
}
