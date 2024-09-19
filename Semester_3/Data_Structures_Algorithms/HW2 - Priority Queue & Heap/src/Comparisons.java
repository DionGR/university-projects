import components.software.Task;
import data_structures.Sort;

/**
 * PART D: Greedy-Decreasing & File Generation
 *
 * We generate a great amount of testfiles with a different amount of tasks.
 * We then run the Greedy and Greedy-Decreasing algorithms for each configuration,
 * and compare the average makespan of the two algorithms.
 *
 *
 * @authors: P3200262, P3200298
 * @info: Made for the course of Data Structures @ AUEB 2021-2022.
 **/


public class Comparisons {
    public static void main(String[] args) {
        final int TASK_CONFIGS = 8;         // Number of task configurations to test. (min 3)
        final int FILES_PER_CONFIG = 20;    // Number of files per task configuration. (min 10)
        int BASE_TASKS_PER_FILE = 50;       // Number of tasks per file. (min -)
    
        System.out.println("Configs: " + TASK_CONFIGS + "\nFiles per config: " + FILES_PER_CONFIG + "\n");
        for(int i = 0; i < TASK_CONFIGS; i++){ // For each task config
            
            if(args.length > 0 && args[0].equals("GENERATE"))                              // Generate data files by choice.
                FileHandler.generate_tests(FILES_PER_CONFIG, BASE_TASKS_PER_FILE);
            
            double unsorted_makespan = 0;
            double sorted_makespan = 0;
            
            for(int j = 0; j < FILES_PER_CONFIG; j++) {                                    // For each file in the config
                String path = "data\\" + FileHandler.create_name(BASE_TASKS_PER_FILE, j);  // Path to file. We know how to generate the name.
                
                try {
                    int processors = FileHandler.processor_count(path);                    // Gets the number of processors.
                    Task[] tasks = FileHandler.extract_tasks(path);                        // Reads the file and stores the data in an array.
        
                    unsorted_makespan += Greedy.Greedy(tasks, processors);                 // Calculates the makespan of the unsorted tasks.
        
                    Sort.sort(tasks);                                                      // Sorts the tasks.
                    sorted_makespan += Greedy.Greedy(tasks, processors);                   // Calculates the makespan of the sorted tasks.
                } catch (Exception e) {
                    System.out.println("Error: " + e);
                }
            }
            
            
            double unsorted_avg = unsorted_makespan/FILES_PER_CONFIG;                       // Calculates the average makespan of the unsorted tasks.
            double sorted_avg = sorted_makespan/FILES_PER_CONFIG;                           // Calculates the average makespan of the sorted tasks.
            
            System.out.printf("Unsorted makespan for N[%d]: %.2f\n", BASE_TASKS_PER_FILE, unsorted_avg);
            System.out.printf("Sorted makespan for N[%d]: %.2f\n", BASE_TASKS_PER_FILE, sorted_avg);
            
            System.out.println("Makespan Reduction %: " + (unsorted_avg - sorted_avg)/Math.abs(unsorted_avg) * 100 + "\n");
            // Calculates the reduction % of the makespan.
    
            BASE_TASKS_PER_FILE *= 1.5;                                                      // Increases the number of tasks per file.
        }
    }
}
