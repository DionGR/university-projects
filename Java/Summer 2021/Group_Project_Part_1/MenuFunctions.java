import java.util.ArrayList;
import java.util.Scanner;

public class MenuFunctions {
    public static Databases database = mainApp.database;
    public static final Scanner in = new Scanner(System.in);

    public static int printMenu() {
        System.out.println("Main Menu");
        System.out.println("0. Exit");
        System.out.println("1. Insert new type of expense");
        System.out.println("2. Insert new employee");
        System.out.println("3. Insert new expense of an employee");
        System.out.println("4. Insert new down payment");
        System.out.println("5. Display employee expenses");
        System.out.println("6. Clear expenses of an employee");
        System.out.println("7. Display employee movements");
        System.out.println("8. Clear expenses of all employees");
        System.out.println("9. Display monthly compensation amount of all employees");
        System.out.println("10. Save file");
        System.out.println("INFO: You may use -1 anytime to return to the Main Menu.");
        System.out.print("Select a mode: ");
        return Integer.parseInt(in.nextLine());
    }

    public static void selection_1() {
        /* Variables that are taken as input and are checked for validity have to be pre-declared due to the behavior of do..while */
        int input;
        double maxMonthlySeverance;

        while (true) {
            try{
                do{
                    System.out.print("Pick the appropriate expense type: [1] Type A [2] Type B\nInput: ");
                    input = Integer.parseInt(in.nextLine());
                }while(input != 1 && input != 2 && input != -1);
                if (exitCheck(input)) break;

                System.out.print("Please give the code of the type: ");
                String code = in.nextLine();
                if (exitCheck(code)) break;

                System.out.print("Please give the description of the expense type: ");
                String description = in.nextLine();
                if (exitCheck(description)) break;

                do {
                    System.out.print("Please give the max monthly severance: ");
                    maxMonthlySeverance = Double.parseDouble(in.nextLine());
                }while(maxMonthlySeverance < -1d); // -1 because it's an option to exit the menu.
                if (exitCheck((int)maxMonthlySeverance)) break;

                if (input == 1) {// Add new expense Type A.
                    double valuePerUnit;
                    do {
                        System.out.print("Please give the value per Unit (e.g 5 Euro per Km): ");
                        valuePerUnit = Double.parseDouble(in.nextLine());
                    }while(valuePerUnit < -1d);
                    if (exitCheck((int)valuePerUnit)) break;

                    System.out.print("Please give the type of the unit: ");
                    String unit = in.nextLine();
                    if (exitCheck(unit)) break;

                    ExpenseTypeA newExpenseTypeA = new ExpenseTypeA(code, description, maxMonthlySeverance,valuePerUnit, unit);
                    database.addExpenseType(newExpenseTypeA);
                    System.out.println("Successfully added new expense type A!\n");
                } else if (input == 2) {// Add new Expense Type B.
                    double percentage;

                    do {
                        System.out.print("Please give the percentage of the value to be reimbursed (e.g. 10, 55, 73 etc): ");
                        percentage = Double.parseDouble(in.nextLine());
                    }while (percentage < -1d);
                    if (exitCheck((int)percentage)) break;

                    ExpenseTypeB newExpenseTypeB = new ExpenseTypeB(code, description, maxMonthlySeverance, percentage);
                    database.addExpenseType(newExpenseTypeB);
                    System.out.println("Successfully added new expense type B!\n");
                }
                break;
            } catch (Exception e){
                System.out.println("Make sure your input is appropriate.\n");
            }
        }
    }

    public static void selection_2() {
        double maxTotalSeverance;

        while (true) {
            try {
                System.out.print("First name of the employee: ");
                String firstName = in.nextLine();
                if (exitCheck(firstName)) break;

                System.out.print("\nLast name of the employee: ");
                String lastName = in.nextLine();
                if (exitCheck(lastName)) break;

                System.out.print("\nCode of the employee: ");
                String code = in.nextLine();
                if (exitCheck(code)) break;

                do {
                    System.out.print("\nMax total severance of the employee: ");
                    maxTotalSeverance = Double.parseDouble(in.nextLine());
                }while (maxTotalSeverance < -1d);
                if (exitCheck((int)maxTotalSeverance)) break;

                Employee newEmployee = new Employee(firstName, lastName, code, maxTotalSeverance);
                database.addEmployee(newEmployee);
                System.out.println("Successfully added new employee!\n");
                break;
            } catch (Exception e) {
                System.out.println("Make sure your input is appropriate.\n");
            }
        }
    }// Add new employee.

    public static void selection_3() {
        int employee_selection;
        int type_selection;
        int value;
        double maxMonthlySeverance;

        while (true) {
            try {
                System.out.println("Choose the employee using the appropriate integer. ");
                ArrayList<Employee> employees = database.getEmployees();
                database.printEmployees();

                do {
                    System.out.print("Employee: ");
                    employee_selection = Integer.parseInt(in.nextLine());
                }while ((employee_selection <= 0 || employee_selection > employees.size()) && employee_selection != -1);
                if (exitCheck(employee_selection)) break;
                employee_selection -= 1;

                System.out.print("Describe the expense: ");
                String description = in.nextLine();
                if (exitCheck(description)) break;

                System.out.print("Give the expense code: ");
                String code = in.nextLine();
                if (exitCheck(code)) break;

                do {
                    System.out.print("Give the maximum monthly severance: ");
                    maxMonthlySeverance = Double.parseDouble(in.nextLine());
                }while (maxMonthlySeverance < -1d);
                if (exitCheck((int)maxMonthlySeverance)) break;

                System.out.print("Give the reason for this expense: ");
                String reason = in.nextLine();
                if (exitCheck(reason)) break;

                do {
                    System.out.print("Give the value of this expense: ");
                    value = Integer.parseInt(in.nextLine());
                }while(value < -1);
                if (exitCheck(value)) break;

                System.out.print("Pick the appropriate expense type: [1] Type A [2] Type B\nInput: ");
                do{
                    System.out.print("Pick the appropriate expense type: [1] Type A [2] Type B\nInput: ");
                    type_selection = Integer.parseInt(in.nextLine());
                }while(type_selection != 1 && type_selection != 2 && type_selection != -1);
                if (exitCheck(type_selection)) break;

                if (type_selection == 1) {
                    double severancePerUnit;
                    do {
                        System.out.print("Severance per unit for this expense: ");
                        severancePerUnit = Double.parseDouble(in.nextLine());
                    }while(severancePerUnit < -1d);
                    if (exitCheck((int)severancePerUnit)) break;

                    System.out.print("Unit: ");
                    String unit = in.nextLine();
                    if (exitCheck(unit)) break;

                    ExpenseTypeA newExpenseTypeA = new ExpenseTypeA(code, description, maxMonthlySeverance, severancePerUnit, unit);
                    EmployeeExpense newEmployeeExpense = new EmployeeExpense(employees.get(employee_selection), newExpenseTypeA, reason, value);
                    database.addExpenseType(newExpenseTypeA);
                    database.addEmployeeExpense(newEmployeeExpense);
                    WriteEmployeeExpenses.appendData(newEmployeeExpense, 1);
                    System.out.println("Appended to file.");
                } else if (type_selection == 2) {
                    double severancePercentage;

                    do {
                        System.out.print("Severance percentage: ");
                        severancePercentage = Double.parseDouble(in.nextLine());
                    }while (severancePercentage < -1d);
                    if (exitCheck((int)severancePercentage)) break;

                    ExpenseTypeB newExpenseTypeB = new ExpenseTypeB(code, description, maxMonthlySeverance, severancePercentage);
                    EmployeeExpense newEmployeeExpense = new EmployeeExpense(employees.get(employee_selection), newExpenseTypeB, reason, value);
                    database.addExpenseType(newExpenseTypeB);
                    database.addEmployeeExpense(newEmployeeExpense);
                    WriteEmployeeExpenses.appendData(newEmployeeExpense, 2);
                    System.out.println("Appended to file.");
                }
                System.out.println("Successfully added new expense to " + employees.get(employee_selection).getName() + " " + employees.get(employee_selection).getLastName() + "\n");
                database.createActivities();
                break;
            } catch (Exception e) {
                System.out.println("Make sure your input is appropriate.\n");
            }
        }
    }// Add new employee expense.

    public static void selection_4() {
        int employee_selection;
        double downpaymentAmount;

        while (true) {
            try {
                do {
                    System.out.println("Choose the employee you wish to add a down payment for: ");
                    database.printEmployees();
                    employee_selection = Integer.parseInt(in.nextLine());
                }while((employee_selection <= 0 || employee_selection > database.getEmployees().size()) && employee_selection != -1);
                if (exitCheck(employee_selection)) break;
                employee_selection -= 1;
                Employee employee = database.getEmployees().get(employee_selection);

                do {
                    System.out.print("Down payment amount: ");
                    downpaymentAmount = Double.parseDouble(in.nextLine());
                }while(downpaymentAmount < -1d);
                if (exitCheck((int)downpaymentAmount)) break;
                DownPayment downpayment = new DownPayment(employee, downpaymentAmount);
                WriteEmployeeActivities.appendData(downpayment, "PROKATAVOLI");
                database.addEmployeeActivity(downpayment);
                break;
            } catch (Exception e) {
                System.out.println("Make sure your input is appropriate.\n");
            }
        }
    }// Add new downpayment.

    public static void selection_5() {
        System.out.println("Employee expenses:");
        database.printExpenses();
        System.out.println("\n");
    }// Print all employee expenses.

    public static void selection_6() {
        int input;

        while (true) {
            try {
                System.out.println("Choose which employee's activities you want to liquidate: ");
                database.printEmployees();
                do {
                    System.out.print("Employee: ");
                    input = Integer.parseInt(in.nextLine());
                }while ((input < 0 || input > database.getEmployees().size()) && input != -1);
                if (exitCheck(input)) break;
                input -= 1;
                database.liquidateEmployee(database.getEmployees().get(input));
                System.out.println("Successfully liquidated employee.\n");
                break;
            } catch (Exception e) {
                System.out.println("Make sure your input is appropriate.\n");
            }
        }
    }// Liquidate an employees activities.

    public static void selection_7() {
        int employee_selection;

        while (true) {
            try {
                System.out.println("Choose which employee's activities you want to display: ");
                database.printEmployees();
                do {
                    System.out.println("Employee: ");
                    employee_selection = Integer.parseInt(in.nextLine());
                }while((employee_selection <= 0 || employee_selection > database.getEmployees().size()) && employee_selection != -1);
                if (exitCheck(employee_selection)) break;
                employee_selection -= 1;
                database.printEmployeeActivities(database.getEmployees().get(employee_selection));
                System.out.print("\n");
                break;
            } catch (Exception e) {
                System.out.println("Make sure your input is appropriate.\n");
            }
        }
    }// Prints all the activities of a specified employee.

    public static void selection_8() {
        System.out.println("Clearing expenses of all employees.");
        database.liquidateAllEmployees();
        System.out.println("Successfully cleared expenses of all employees!\n");
    }// Liquidates the expenses of a specific employee.

    public static void selection_9() {
        System.out.println("Printing...");
        database.liquidateAllEmployees();
        database.printFinalActivities();
        System.out.print("\n");
    }// Prints all the activities.

    public static boolean exitCheck(int input){
        if(input == -1){
            System.out.println("Exiting...\n");
            return true;
        }
        return false;
    }// Exit check for integers/doubles.

    public static boolean exitCheck(String input){
        if(input.equals("-1")){
            System.out.println("Exiting...\n");
            return true;
        }
        return false;
    }// Exit check for strings.
}
