// This file reads the items that are inside Employee Expenses file

import java.util.ArrayList;
import java.util.HashMap;

public class ReadEmployeeExpenses {

    public static Databases database = mainApp.database;

    public static void initializeEmployeeExpenses() throws Exception {
        // startTag = the starting tag on top of the file
        String startTag = "EXPENSE_LIST";
        // startTag = the starting tag on top of the file
        String openTag = "EXPENSE";
        // requiredTags = tags that are required to be inside every item
        ArrayList<String> requiredTags = new ArrayList<>();
        requiredTags.add("EMPLOYEE_CODE");
        requiredTags.add("EXPENSE_TYPE");
        requiredTags.add("EXPENSE_CODE");
        requiredTags.add("VAL");
        String fileName = "DataFiles/EMPLOYEE_EXPENSE_FILE.txt";
        // employeeExpenses = has the content of the employee expenses file
        ArrayList<String> employeeExpenses = ReadFile.fileToArrayList(fileName);

        // Checks if the file has the correct format
        if (ReadFile.hasCorrectFormat(startTag, openTag, requiredTags, employeeExpenses)) {
            // data = ArrayList that each item has a HashMap that maps String to String
            // the key of the HashMap is the tag and the value has the key's value
            ArrayList<HashMap<String, String>> data = ReadFile.getData();
            for (HashMap<String, String> item : data) {
                // gets the value of the key with name EMPLOYEE_CODE
                String employeeCode = item.get("EMPLOYEE_CODE");
                // employeeIdx is used to check if the employee with a certain code is inside database
                // if there is, it extracts it's items
                int employeeIdx = database.getEmployeeByCode(employeeCode);
                String reason = item.get("REASON");
                double val = Double.parseDouble(item.get("VAL"));
                int expenseType = Integer.parseInt(item.get("EXPENSE_TYPE"));
                if (employeeIdx == -1) System.out.println("Couldn't find employee with code " + employeeCode);
                else {
                    String expenseCode = item.get("EXPENSE_CODE");
                    // expenseIdx is used to check if there is an ExpenseCode with the provided code
                    int expenseIdx = database.getExpenseByCode(expenseCode);
                    if (expenseIdx == -1) System.out.println("Couldn't find expense with code " + expenseCode);
                    else {
                        // Creates employee, expense and puts them as parameters to create a new employeeExpense
                        // and appends it to database
                        Employee employee = database.getEmployees().get(employeeIdx);
                        ExpenseType expense = database.getExpenseTypes().get(expenseIdx);
                        EmployeeExpense employeeExpense = new EmployeeExpense(employee, expense, reason, val);
                        database.addEmployeeExpense(employeeExpense);
                    }
                }
            }

        } else System.out.println("Error, wrong file format for the file " + fileName + ", please check format.");
    }

}
