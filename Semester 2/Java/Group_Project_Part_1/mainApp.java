/*
Java - Hristos Koutsikas 2021 Summer
TEAM NUMBER 69
DIONYSIOS RIGATOS P3200262
PAPAPOSTOLOU CHRISTOFOROS P3150208
PAPAPANAGIOTOU ANASTASIOS P3200143
 */

public class  mainApp{
    public static Databases database = new Databases(); // Creating our main database.

    public static void main (String[] args) throws Exception {
        ReadExpenseType.initializeExpenseType();
        ReadEmployeeList.initializeEmployeeList();
        ReadEmployeeExpenses.initializeEmployeeExpenses();
        ReadEmployeeActivities.initializeEmployeeActivities();
        database.createActivities();
        System.out.println("Successfully initialized database.");

        loop: while(true) {
            try{
                int selection = MenuFunctions.printMenu();
                switch (selection) {
                    case 0:
                        System.out.println("Exiting...");
                        if (WriteEmployeeActivities.isOpened) {
                            WriteEmployeeActivities.closeFile();
                    }
                        if (WriteEmployeeExpenses.isOpened) {
                            WriteEmployeeExpenses.closeFile();
                        }
                        break loop;
                    case 1:
                        MenuFunctions.selection_1();
                        break;
                    case 2:
                        MenuFunctions.selection_2();
                        break;
                    case 3:
                        WriteEmployeeExpenses.openFile();
                        MenuFunctions.selection_3();
                        break;
                    case 4:
                        WriteEmployeeActivities.openFile();
                        MenuFunctions.selection_4();
                        break;
                    case 5:
                        MenuFunctions.selection_5();
                        break;
                    case 6:
                        MenuFunctions.selection_6();
                        break;
                    case 7:
                        MenuFunctions.selection_7();
                        break;
                    case 8:
                        MenuFunctions.selection_8();
                        break;
                    case 9:
                        MenuFunctions.selection_9();
                        break;
                    case 10:

                        if (WriteEmployeeActivities.isOpened) {
                            WriteEmployeeActivities.closeFile();
                            System.out.println("Closed file for Employee Activities.");
                    }
                        if (WriteEmployeeExpenses.isOpened) {
                            WriteEmployeeExpenses.closeFile();
                            System.out.println("Closed file for Employee Expenses.");
                        }
                        break;
                    default:
                        System.out.println("Wrong selection, try again.\n");
                }
            } catch (Exception e) {
                System.out.println("Please type an integer.\n");
                System.out.println("error: " + e.getMessage());
            }
        }
    }
}
