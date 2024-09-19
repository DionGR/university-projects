import components.hardware.Processor;
import components.software.Task;
import data_structures.MaxPQ;

/**
 * PART B: Greedy.
 *
 * This implementation assigns each task by order of arrival,
 * to the processor with the highest priority in Priority Queue
 * (the one with the minimum active time).
 *
 *
 * @authors: P3200262, P3200298
 * @info: Made for the course of Data Structures @ AUEB 2021-2022
 **/


public class Greedy {
    
    public static void main(String[] args) {
        try{
            int processors = FileHandler.processor_count(args[0]);          // Gets the number of processors.
            Task[] tasks = FileHandler.extract_tasks(args[0]);              // Gets the tasks from the file.
            
            System.out.println("Makespan: " + Greedy(tasks, processors));   // Prints the makespan after calculating it.
        }catch (Exception e){
            System.out.println("Error: " + e);
        }
    }
    
    
    /**
     * PART B-D: Calculates the makespan of the given tasks and processors.
     *
     * @param tasks
     * @param processors
     * @return int
     */
    public static int Greedy(Task[] tasks, int processors){
        
        MaxPQ<Processor> priority_manager = new MaxPQ<>();  // Creates a new processor queue.
        
        for(int i = 0; i < processors; i++)
            priority_manager.insert(new Processor(i + 1));
        // Inserts the processors into the processor queue, giving them a unique ID.
    
        Processor processor = null;
        for (Task task: tasks) {
            processor = priority_manager.getMax();     // Gets the processor with the highest priority.
            processor.addTask(task);                   // Assigns the task to the processor.
            priority_manager.insert(processor);        // Inserts the processor back into the queue.
        }
        
        while (!priority_manager.isEmpty()) {
            processor = priority_manager.getMax();
            
            if(tasks.length < 50) System.out.print(processor);     // Prints the processors with their tasks, if the tasks are less than 50.
        }// Gets the processor with the highest priority until the one with the least priority, which makes the makespan.
        
        return processor.getActiveTime();            // The makespan is the time of the longest active processor in the PQ (last in PQ is the one with the least priority).
    }
}
