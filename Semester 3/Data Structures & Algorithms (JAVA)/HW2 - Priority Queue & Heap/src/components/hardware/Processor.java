package components.hardware;

import data_structures.ElementaryArrayList;
import components.software.Task;

/**
 * PART A: Processor item class.
 *
 * A class that represents a processor.
 * Has a unique ID  and a list of tasks that have been completed.
 * It is comparable according to the instructions;
 * First we compare the completed task time and then the IDs.
 *
 * @authors: P3200262, P3200298
 * @info: Made for the course of Data Structures @ AUEB 2021-2022
 **/


public class Processor implements Comparable<Processor>{
    private final int ID;
    private final ElementaryArrayList<Task> processedTasks;
    
    public Processor(int ID){
        this.ID = ID;
        this.processedTasks = new ElementaryArrayList<>();
    }// Main constructor.
    
    public void addTask(Task task){
        processedTasks.add(task);
    }// Add task to the list of processed tasks.

    public int getActiveTime(){
        int activeTime = 0;
        
        if (processedTasks.size() == 0) return 0;
        
        for(int i = 0; i < processedTasks.size(); i++)
            activeTime += processedTasks.get(i).getTime();
        return activeTime;
    }// Calculates the processor's activity time from all the tasks completed in the list.
    
    @Deprecated
    public int getID(){
        return ID;
    }// Returns the processor's unique ID.
    
    @Override
    public String toString(){
        return "[Processor ID: " + ID + " | Load: " + getActiveTime() + "]: Tasks " + processedTasks + "\n";
    }// Returns the processor's ID and the list of tasks completed.
    
    @Override
    public int compareTo(Processor other) {
        int difference = other.getActiveTime() - this.getActiveTime();
        if (difference == 0) return other.ID - this.ID;
        return difference;
    }// Compares the processor's activity time with another processor's activity time. If equal, it compares the ID's.
}
