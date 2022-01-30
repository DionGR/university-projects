// Auto to arxeio diabazei ta stoixeia pou brhskontai mesa sto arxeio me tis kinhseis ergazomenon
// This file reads the contents of the file with employee activities

import java.util.ArrayList;
import java.util.HashMap;

public class ReadEmployeeActivities {

    public static Databases database = mainApp.database;

    public static void initializeEmployeeActivities() throws Exception {
        // startTag = the starting tag on top of the file
        String startTag = "TRN_LIST";
        // openTag = tag name before each item
        String openTag = "TRN";
        // requiredTags = tags that are required to be inside every item
        ArrayList<String> requiredTags = new ArrayList<>();
        requiredTags.add("EMPLOYEE_CODE");
        requiredTags.add("TYPE");
        requiredTags.add("VAL");
        String fileName = "DataFiles/EMPLOYEE_ACTIVITY_FILE.txt";
        // employeeActivities = has the content of the employee activity file
        ArrayList<String> employeeActivities = ReadFile.fileToArrayList(fileName);

        // Checks if the file has the correct format
        if (ReadFile.hasCorrectFormat(startTag, openTag, requiredTags, employeeActivities)) {
            // data = ArrayList that each item has a HashMap that maps String to String
            // the key of the HashMap is the tag and the value has the key's value
            ArrayList<HashMap<String, String>> data = ReadFile.getData();

            for (HashMap<String, String> item : data) {
                // gets the value of the key with name EMPLOYEE_CODE
                String employeeCode = item.get("EMPLOYEE_CODE");
                // gets the value of the key with name TYPE
                String type = item.get("TYPE");
                // chooses the VAL and converts it to double
                double value = Double.parseDouble(item.get("VAL"));
                // employeeIdx is used to check if the employee with a certain code is inside database
                // if there is, it extracts it's items
                int employeeIdx = database.getEmployeeByCode(employeeCode);
                if (employeeIdx == -1) {
                    System.out.println("Couldn't find employee with code " + employeeCode);
                    database.printEmployees();
                }
                else {
                    Employee employee = database.getEmployees().get(employeeIdx);
                    switch (type.toUpperCase()) {
                        // if there is a downpayment create a new DownPayment and append it to the database
                        case "PROKATAVOLI":
                            DownPayment downpayment = new DownPayment(employee, value);
                            database.addEmployeeActivity(downpayment);
                            break;
                        case "APOZIMIOSI":
                            String expenseType = item.get("EXPENSE_TYPE");
                            String expenseCode = item.get("EXPENSE_CODE");
                            // expenseIdx is used to check if there is an ExpenseCode with the provided code
                            int expenseIdx = database.getExpenseByCode(expenseCode);
                            if (expenseIdx == -1) System.out.println("Couldn't find expense with code " + expenseCode);
                            else {
                                // if it is severance, create a new Severance and append it to the database
                                ExpenseType expenseTypeActivity = database.getExpenseTypes().get(expenseIdx);
                                Severance severance = new Severance(employee, expenseTypeActivity, value);
                                database.addEmployeeActivity(severance);
                            }
                            break;
                        case "DIAFORA":
                            // creates difference and adds it to the database
                            Difference difference = new Difference(employee, value);
                            database.addEmployeeActivity(difference);
                            break;
                        case "FINAL_APOZIMIOSI":
                            // creates FinalPayment and adds it to the database
                            FinalPayment finalPayment = new FinalPayment(employee, value);
                            database.addEmployeeActivity(finalPayment);
                            break;
                        default:
                            System.out.println("Couldn't find activity " + type);
                    }

                }
            }
        } else System.out.println("Error, wrong file format for the file " + fileName + ", please check format.");

    }
}
