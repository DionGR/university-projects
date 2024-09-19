import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class ReadFile {

    // Reader and in are used to read the file
    private static FileReader reader;
    private static BufferedReader in;
    // data is used to store contents of the file it read
    private static ArrayList<HashMap<String, String>> data;

    // hasNum is a method that checks if a String has a number and returns its position
    public static int hasNum(String line) {
        for (int i = 0; i < line.length(); i++) {
            if (Character.isDigit(line.charAt(i))) return i;
        }

        return -1;
    }

    // removes all white spaces until it reaches a character other than a white space
    // returns the string without the starting white spaces
    public static String removeFirstWhiteSpaces(String line) {
        int i = 0;
        while (i < line.length()-1 && line.charAt(i) == ' ')
            i++;

        return line.substring(i);
    }

    // reads the file and appends the contents of the file to the ArrayLists
    public static ArrayList<String> fileToArrayList(String fileName) throws Exception {
        // file = path of the file
        File file = new File(fileName);
        reader = new FileReader(file);
        in = new BufferedReader(reader);
        // stores the contents of the file
        ArrayList<String> fileContent = new ArrayList<>();
        // removes all whitespaces of the first line, if it has any
        String line = in.readLine().replaceAll(" ", "");
        // checks if there are blank lines at the start
        while (line.equals("")) line = in.readLine();
        // appends the first line to fileContent
        fileContent.add(line);

        // while line is not null, meaning there is content to read
        while (line != null) {
            line = in.readLine();
            // if the line is not null or white space, append it to the arraylist removing its whitespaces
            if (line!= null && !line.equals("")) fileContent.add(removeFirstWhiteSpaces(line));
        }
        // closes the files when it reads them
        if (in != null) in.close();
        if (reader != null) reader.close();
        // returns the arraylist with the contents of the file
        return fileContent;
    }

    // converts String to a HashMap (Tag maps value)
    public static HashMap<String, String> tagsToMap(ArrayList<String> items) {

        // HashMap where items are stored
        HashMap<String, String> hash = new HashMap<>();
        for (String item : items) {
            String key, value;

            // if it contains " it means that it is of type String and we don't want to remove whitespaces
            if (item.contains("\"")) {
                // splits the item based on "
                String[] data = item.split("\"");
                // the first item of the array will be the tag and we remove all white spaces because tags don't have any
                key = data[0].replaceAll(" ", "");
                // value is the text between the two "
                value = data[1];

            } else {
                // it has a number
                // find the idx of the first number it finds in the string
                int idx = hasNum(item);
                if (idx == -1) {
                    key = "nonreal";
                    value = "nonreal";
                } else {
                    // splits the line from the start until the idx of the number and removes all white spaces
                    key = item.substring(0, idx).replaceAll(" ", "");
                    // the value is from the idx until the end
                    value = item.substring(idx);
                }
            }
            hash.put(key, value);
        }

        return hash;
    }

    // checks if an item has the required tags and returns true/false
    public static boolean checkTags(ArrayList<String> requiredTags, ArrayList<String> currentTags) {
        // copy's contents are all the required tags
        ArrayList<String> copy = new ArrayList<>(requiredTags);
        // hash converts the current tags, the ones that are from the file that got read, to HashMap
        HashMap<String, String> hash = tagsToMap(currentTags);
        String firstKey = hash.entrySet().iterator().next().getKey();
        if (firstKey.equals("Error")) return false;
        // for every key in the HashMap
        for (String key : hash.keySet()) {

            key = key.toUpperCase().replaceAll(" ", "");
            // deleted the key from the required tags, if is contains it
            copy.remove(key);
        }

        // appends the tags to data
        data.add(hash);
        // if all required tags are deleted it means that the item has all the required tags
        return copy.size() == 0;
    }

    // checks if the file has the right format
    public static boolean hasCorrectFormat(String startTag, String openTag, ArrayList<String> requiredTags, ArrayList<String> fileContent) {
        data = new ArrayList<>();
        // returns false if it does not start with startTag
        if (!fileContent.get(0).equalsIgnoreCase(startTag)) return false;
        // returns false if the second char is not {
        if (!fileContent.get(1).equals("{")) return false;
        int i = 2;
        // while line is not the last } of the file
        while (i < fileContent.size()-1) {
            // if every item does not start with openTag return false
            if (!fileContent.get(i).equalsIgnoreCase(openTag)) return false;
            i++;
            if (!fileContent.get(i).equals("{")) return false;
            i++;
            ArrayList<String> tags = new ArrayList<>();
            // while we don't have reached } of the item
            while (!fileContent.get(i).equals("}")) {
                // if there is a second { inside the item or we have reached the end of the fileContent without finding } return false
                if (fileContent.get(i).equals("{") || i == fileContent.size()-1) return false;
                // append tags with their values to tags
                tags.add(fileContent.get(i));
                i++;
            }
            // containsTags = true if it contains all required tags
            boolean containsTags = checkTags(requiredTags, tags);
            if (!containsTags) return false;
            i++;
            if (i == fileContent.size()) return false;
        }

        return true;
    }

    // getter for the data
    public static ArrayList<HashMap<String, String>> getData() {
        return data;
    }
}
