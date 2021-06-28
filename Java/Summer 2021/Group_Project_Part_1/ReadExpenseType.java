import java.util.ArrayList;
import java.util.HashMap;

public class ReadExpenseType {

    public static Databases database = mainApp.database;

    public static void initializeExpenseType() throws Exception {
        // startTag = the starting tag on top of the file
        String startTag = "EXPENSE_TYPE_LIST";
        // openTag = tag name before each item
        String openTag = "EXPENSE_TYPE";
        // requiredTags = tags that are required to be inside every item
        ArrayList<String> requiredTags = new ArrayList<>();
        requiredTags.add("TYPE");
        requiredTags.add("CODE");
        requiredTags.add("DESCR");
        String fileName = "DataFiles/EXPENSE_TYPE_FILE.txt";
        // expenseTypes = has the content of the employee expenses file
        ArrayList<String> expenseTypes = ReadFile.fileToArrayList(fileName);

        // Checks if the file has the correct format
        if (ReadFile.hasCorrectFormat(startTag, openTag, requiredTags, expenseTypes))
        {
            // data = ArrayList that each item has a HashMap that maps String to String
            // the key of the HashMap is the tag and the value has the key's value
            ArrayList<HashMap<String, String>> data = ReadFile.getData();
            for (HashMap<String, String> item : data) {
                // gets the value of the key with name CODE
                String code = item.get("CODE");
                // gets the value of the key with name DESCR
                String description = item.get("DESCR");
                // gets the value of the key with name MAXMONTHLYSEVERANCE
                double maxMonthlySeverance = Double.parseDouble(item.get("MAXMONTHLYSEVERANCE"));

                // if the expense is of typeA
                if (Integer.parseInt(item.get("TYPE")) == 1) {
                    double valuePerUnit = Double.parseDouble(item.get("VALUEPERUNIT"));
                    String unit = item.get("UNITTYPE");
                    // creates a new ExpenseTypeA with the info it read
                    ExpenseTypeA expenseTypeA = new ExpenseTypeA(code, description, maxMonthlySeverance, valuePerUnit, unit);
                    database.addExpenseType(expenseTypeA);
                } else {
                    double severancePercentage = Double.parseDouble(item.get("SEVERANCEPERCENTAGE"));
                    // creates a new ExpenseTypeB with the info it read
                    ExpenseType expenseTypeB = new ExpenseTypeB(code, description, maxMonthlySeverance, severancePercentage);
                    database.addExpenseType(expenseTypeB);
                }
            }
        } else System.out.println("Error, wrong file format for the file " + fileName + ", please check the format.");

    }

}
