import java.io.FileReader;       // FileReader module for reading the file.
import java.io.BufferedReader;   // BufferedReader module for the file stream.
import java.util.regex.Pattern;  // Pattern module for creating the pattern.
import java.util.regex.Matcher;  // Matcher module for matching the tags using the pattern.

import data_structures.StringStackImpl;

/**
 * PART B: Tag Matching
 *
 * This is an HTML Tag Matching class.
 * It utilizes the previously created StringStackImpl class to store the tags and check if they do close.
 *
 * @authors: P3200262, P3200298
 * @info: Made for the course of Data Structures @ AUEB 2021-2022
 **/


class TagMatching{
    public static void main(String[] args){

        try {
            BufferedReader br = new BufferedReader(new FileReader(args[0]));    // Open the file using the given filename.
            boolean invalid = true;

            StringStackImpl<String> stack = new StringStackImpl<>();
            String line;

            if(br.ready()){                                                     // Check if the file has content to begin with.

                Matcher open;                                                   // Create a matcher for the open tags.
                Pattern html_tag = Pattern.compile("(<[^/<>]+>)|(</[^/<>]+>)"); // Create a pattern for html tags and split it into group 1(open tags) and group 2(closed tags).

                while((line = br.readLine()) != null) {                         // Read the file line by line while there are lines to read.
                    open = html_tag.matcher(line);

                    while(open.find()) {                                        // While there are tags in the line.
                        if (open.group(1) != null) {                            // If the tag is an open tag.
                            stack.push(open.group(1));                          // Push the tag to the stack.
                        }else {
                            if(!stack.isEmpty()){
                                if (stack.peek().equals(open.group(2).replaceAll("/", ""))) {
                                    stack.pop();                                // If the tag is a closed tag, pop the top of the stack and check if it matches the closed tag.
                                    invalid = false;
                                }
                            } else {                                            // If the tag is not a valid tag, set the invalid flag to true and end the loop.
                                invalid = true;
                                break;
                            }
                        }
                    }
                }
            }
            br.close();

            if(invalid || !stack.isEmpty()) {                                    // If the stack is empty or the invalid flag is true, print the error message.
                System.out.println("The HTML document is not valid.");
            }else{
                System.out.println("The HTML document is valid.");
            }

        } catch (Exception e) {
            if(e instanceof java.io.FileNotFoundException || e instanceof ArrayIndexOutOfBoundsException)
                System.out.println("Incorrect input. The file is not found.");   // If no input is given or the file does not exist, print the error message.
            else
                System.out.println("Error: " + e.getMessage());                  // If there is another error, print the error message.
        }
    }
}