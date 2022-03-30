import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.IOException; 
import java.io.FileReader;
import java.util.Random;

import components.software.Task;

/**
 * HELPER: FileHandler
 * Reads a file of the given form and extracts all the data from it.
 * Ensures the number of processors and tasks is correct.
 *
 * @authors: P3200262, P3200298
 * @info: Made for the course of Data Structures @ AUEB 2021-2022
 **/


public class FileHandler{
    
    /**
     * HELPER: Takes a file name and returns an array of tasks containing the data from the file.
     * 
     * @param filename
     * @return file_data
     */
    public static Task[] extract_tasks(String filename) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(filename));  // Opens the file buffer.
        
        br.readLine(); // Reads the number of processors. Redundant in here.
        
        int task_count = line_count(filename) - 2;
        if (task_count <= 0 || task_count != Integer.parseInt(br.readLine())) throw new IllegalArgumentException("Number of tasks is not correct.");
        // Reads the number of tasks and checks if the number of tasks is positive and valid.
        
        Task[] tasks = new Task[task_count];    // Creates an array of tasks to store the file's data.
        String line;                            // Will store the current line.

        int line_count = 0;
        while ((line = br.readLine()) != null) {      // While the end of the file has not been reached.
            String[] data = line.split(" ");    // Store the task id and time extracted in a String array.
            tasks[line_count++] = new Task(Integer.parseInt(data[0]), Integer.parseInt(data[1]));  
        }// Read the file line by line and store the extracted data in the task array.
        
        br.close();
        
        return tasks;
    }
    
    
    /**
     * HELPER: Takes the amount of files and the amount of tasks per file and creates them.
     *
     * @param file_amount
     * @param n
     */
    public static void generate_tests(int file_amount, int n){
        PrintWriter write_file = null;
        Random random_generator = new Random();                         // Creates a random generator.
        
        for(int i = 0; i < file_amount; i++){                           // For each file.
            String file_name = create_name(n, i);                       // Creates the file name.
            
            try{
                write_file = create_file("data\\" + file_name); // Creates the file.
                write_file.println((int) Math.floor(Math.sqrt(n)));     // Writes the number of processors.
                write_file.println(n);                                  // Writes the number of tasks.
                
                for (int j = 0; j < n; j++)                             // Generate ID and time for each task.
                    write_file.println((j + 1) + " " + (random_generator.nextInt(100) + 1));
                
            }catch (Exception e){
                System.out.println("Error: " + e);
            }finally {
                if(write_file != null) write_file.close();              // Closes the file.
            }
        }
    }
    
    
    /**
     * HELPER: Takes a file name and returns the number of processors it contains.
     *
     * @param filename
     * @return processor_count
     */
    public static int processor_count(String filename) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(filename));   // Open the file buffer.
        int count = Integer.parseInt(br.readLine());                        // The number of processors is always in the first line.
        br.close();
        
        if (count <= 0) throw new IllegalArgumentException("Number of processors must be positive.");
        return count;
    }
    
    
    /**
     * HELPER: Takes a file name and returns the number of lines it contains.
     * 
     * @param filename
     * @return line_count
     */
    private static int line_count(String filename) throws IOException {
        BufferedReader line_reader = new BufferedReader(new FileReader(filename));
        int count = (int)(line_reader.lines().count());
        line_reader.close();
        return count;
    }
    
    
    /**
     * HELPER: Takes a file name and creates it with the PrintWriter class.
     *
     * @param file_name
     * @return PrintWriter
     * @throws Exception
     */
    public static PrintWriter create_file(String file_name) throws Exception{
        return new PrintWriter(file_name);
    }
    
    
    /**
     * HELPER: Takes a file name and returns the name of the file according to the given parameters.
     *
     * @param n
     * @param j
     * @return file_name
     */
    public static String create_name(int n, int j){
        return String.valueOf(n) + "_test_" + String.valueOf(j + 1) + ".txt";
    }
}
