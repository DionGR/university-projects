import java.util.ArrayList;
import java.util.HashMap;

public class ReadEmployeeList {

    public static Databases database = mainApp.database;

    public static void initializeEmployeeList() throws Exception {
        // startTag = the starting tag on top of the file
        String startTag = "EMPLOYEE_LIST";
        // openTag = tag name before each item
        String openTag = "EMPLOYEE";
        // requiredTags = tags that are required to be inside every item
        ArrayList<String> requiredTags = new ArrayList<>();
        requiredTags.add("CODE");
        requiredTags.add("SURNAME");
        requiredTags.add("FIRSTNAME");
        String fileName = "DataFiles/EMPLOYEE_FILE.txt";
        // employeeList = has the content of the employee file
        ArrayList<String> employeeList = ReadFile.fileToArrayList(fileName);

        if (ReadFile.hasCorrectFormat(startTag, openTag, requiredTags, employeeList)) {
            ArrayList<HashMap<String, String>> data = ReadFile.getData();
            for (HashMap<String, String> item : data) {
                // reads values of the tags using the keys and they are used as parameters
                // to create a new employee and append it to the Database
                String code = item.get("CODE");
                String firstName = item.get("FIRSTNAME");
                String lastName = item.get("SURNAME");
                double maxTotalSeverance = Double.parseDouble(item.get("MAX_MONHTLY_SEVERANCE"));
                Employee employee = new Employee(firstName, lastName, code, maxTotalSeverance);
                database.addEmployee(employee);
            }
        } else System.out.println("Error, wrong file format for the file " + fileName + ", please check the format.");
    }
}
