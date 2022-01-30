import java.util.ArrayList;
/*
This class is used for storing a lot of necessary information regarding the employees and their expenses/activities.
It also performs a lot of the calculations when it comes to severance and expenses.
 */
public class Databases {
    private ArrayList<Employee> employees;
    private ArrayList<ExpenseType> expenseTypes;
    private ArrayList<EmployeeExpense> employeeExpenses;
    private ArrayList<EmployeeActivity> employeeActivities;

    public Databases() {
        this.employees = new ArrayList<>();
        this.expenseTypes = new ArrayList<>();
        this.employeeExpenses = new ArrayList<>();
        this.employeeActivities = new ArrayList<>();
    }// Constructor.

    public double calculateEmployeeSeverance(Employee employee, ExpenseType expenseType) {
        double sum = 0;
        for (EmployeeExpense employeeExpense: employeeExpenses) {
            if (employeeExpense.belongsTo(employee, expenseType.getCode())) {
                sum += employeeExpense.calculateSeverance();
            }
        }
        return sum;
    }// Calculates the severance of a specific employee.

    public double calculateFinalSeverance(Employee employee) {
        double sum = 0;
        for (EmployeeActivity activity:employeeActivities) {
            if (activity.belongsTo(employee)) {
                if (activity instanceof Severance) { // If the activity is of type Severance we add to the sum
                    sum += activity.getValue();
                }else if (activity instanceof Difference || activity instanceof DownPayment) { // If the activity is of the type Difference or Down Payment we remove from the sum.
                    sum -= activity.getValue();
                }
            }
        }
        return sum;
    }// Calculates the Employees final Severance according to the formula Sum(severance) - Sum(down_payment) - difference

    public void createDifference(Employee employee, double sum) {
        if (sum > employee.getMaxTotalSeverance()) {
            Difference difference = new Difference(employee, sum - employee.getMaxTotalSeverance());
            addEmployeeActivity(difference);
        }
    }// Creates a new difference activity.

    public void createActivities() {
        for (Employee employee: employees) {
            //Loops through the Employees List
            double employeeSum = 0;
            for (ExpenseType expenseType: expenseTypes) {
                //Loops through the Expense Types for each Employee
                double sum = calculateEmployeeSeverance(employee,expenseType);
                //Calls the calculateEmployeeSeverance Method to find the total severance of that Employee for that specific Expense Type
                if (sum > 0) { //If the sum is 0 then it means the Employee hasn't "used" this Expense Type and we shouldn't add it to the Activities.
                    double maxMonthlySeverance = expenseType.getMaxMonthlySeverance();
                    if (maxMonthlySeverance != 0 && sum <= maxMonthlySeverance) {//According to the instructions we should only check the severance margins if the Expense Type value field is NOT 0
                        Severance severance = new Severance(employee, expenseType, sum); //Since we are below the Expense Type severance threshold we can create a new Severance movement and add it to the list
                        if (!employeeActivities.contains(severance)) employeeActivities.add(severance);
                        employeeSum += sum;
                    }else if (sum > maxMonthlySeverance) { //Since we are above the Expense Type severance threshold we will create a Severance activity but only give money equal to the threshold.
                        Severance severance = new Severance(employee, expenseType, maxMonthlySeverance);
                        if (!employeeActivities.contains(severance)) employeeActivities.add(severance);
                        employeeSum += sum;
                    }else if (maxMonthlySeverance == 0) { //According to the Instructions if the severance threshold value equals 0 then we should perform no checks and just add the Severance Activity as it is.
                        Severance severance = new Severance(employee, expenseType, sum);
                        if (!employeeActivities.contains(severance)) employeeActivities.add(severance);
                        employeeSum += sum;
                    }
                }
            }
            createDifference(employee, employeeSum); // Checks if the Sum is bigger than the Employee's Max Monthly and if it is creates a Difference Activity and adds it to the Activities List
        }
    }/* For every Employee calculates the total severance PER EXPENSE TYPE.
    After a specific Expense Type is done being calculated(for a specific Employee),
    a new Severance Activity is created and added to the collection.
    Then it moves on to the next Expense Type of that same Employee.
    After all the Expense Types for an Employee are identified calculated and added to the list
    we move on the next Employee.
    */

    public void clearPastLiquidations(Employee employee) {
        int i = 0;
        while (i < employeeActivities.size()) {
            if (employeeActivities.get(i) instanceof FinalPayment && employeeActivities.get(i).belongsTo(employee)) {
                employeeActivities.remove(i);
            } else {
                i++;
            }
        }
    }// Removes all activities coming from previous liquidations

    public void liquidateAllEmployees() {
        for (Employee employee:employees) {
            liquidateEmployee(employee);
        }
    }// Liquidates all the employee activities.

    public void liquidateEmployee(Employee employee) {
        double value = calculateFinalSeverance(employee);
        FinalPayment finalPayment = new FinalPayment(employee, value);
        clearPastLiquidations(employee);
        employeeActivities.add(finalPayment);
    }// Liquidates an Employees Activities. Calculating the Final Amount to be repaid and added to the Activities List.

    public void printEmployees() {
        int i = 1;
        for (Employee employee: employees) {
            System.out.println(i++ + ". " + employee);
        }
    }// Prints all the Employees and numbers them by the order they were added.

    public void printExpenses() {
        for (EmployeeExpense expense: employeeExpenses) {
            System.out.println(expense);
        }
    }// Prints all the employee expenses.

    public void printFinalActivities() {
        for (EmployeeActivity activity: employeeActivities) {
            if (activity instanceof FinalPayment) {
                System.out.println(activity);
            }
        }
    }// Prints all the activities recorded.

    public void printEmployeeActivities(Employee employee) {
        int i = 0;
        for (EmployeeActivity activity: employeeActivities){
            if (activity.belongsTo(employee)) {
                System.out.println(activity);
                i++;
            }
        }
        System.out.println((i > 0) ? "\n" : "This employee has no recorded activities.");
    }// Prints the activities of a specific employee, if they have any.

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }// Adds an employee to the database.

    public void addExpenseType(ExpenseType expenseType) {
        expenseTypes.add(expenseType);
    }// Adds an expense type to the database.

    public void addEmployeeExpense(EmployeeExpense expense) {
        employeeExpenses.add(expense);
    }// Adds an employee expense to the database.

    public void addEmployeeActivity(EmployeeActivity activity) {
        employeeActivities.add(activity);
    }// Adds an employee activity to the database.

    public ArrayList<Employee> getEmployees() {
        return this.employees;
    }// Returns a list of all the employees.
}
