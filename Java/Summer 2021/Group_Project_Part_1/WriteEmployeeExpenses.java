import java.io.*;
import java.util.ArrayList;

public class WriteEmployeeExpenses {

    // writer and buffer are used to write in the file
    private static FileWriter writer;
    private static BufferedWriter buffer;
    // checks if the file is opened
    public static boolean isOpened;
    private static String fName = "DataFiles/EMPLOYEE_EXPENSE_FILE.txt";
    private static String fileContent;

    // reads the file and puts its contents in the string fileContent
    public static void getCurrentData(File file) throws IOException {
        FileReader reader = new FileReader(file);
        BufferedReader in = new BufferedReader(reader);

        // reads the file
        String line = in.readLine();
        String data = "";
        while (line != null) {
            data += line + "\n";
            line = in.readLine();
        }
        fileContent = data;

        // closes the file
        if (reader != null) { reader.close(); }
        if (in != null) { in.close(); }
    }

    // open the file to write
    public static void openFile() throws IOException {
//        String path = new File(".").getCanonicalPath() + "\\src\\DataFiles\\";
        File file = new File(fName);
        getCurrentData(file);
        writer = new FileWriter(file);
        buffer = new BufferedWriter(writer);
        isOpened = true;
    }

    // creates the string which contains the new info which will be appended to the file
    public static String getDataString(EmployeeExpense employeeExpense, int expenseType) {
        String openString = "    EXPENSE\n    {\n";
        String employeeCode = employeeExpense.getEmployee().getCode();
        double value = employeeExpense.getExpenseValue();
        String expenseCode = employeeExpense.getExpenseType().getCode();
        String reason = employeeExpense.getReason();

        openString += "        EMPLOYEE_CODE \"" + employeeCode + "\"\n";
        openString += "        EXPENSE_TYPE " + expenseType + "\n";
        openString += "        EXPENSE_CODE \"" + expenseCode + "\"\n";
        openString += "        REASON \"" + reason + "\"\n";
        openString += "        VAL " + value + "\n    }\n";
        return openString;
    }

    // appends the string from getDataString to the end of the file
    public static void appendData(EmployeeExpense employeeExpense, int expenseType) throws IOException {
        String dataString = getDataString(employeeExpense, expenseType);
        // appends finalContent without the last } with dataString and a } in the end
        String finalString = fileContent.substring(0, fileContent.length()-2) + dataString + "}";
        writer.write(finalString);
    }

    // closes the file
    public static void closeFile() throws IOException {
        buffer.close();
        isOpened = false;
    }

}
