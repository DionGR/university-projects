/*
This class holds information regarding a specific Employee expense.
 */
public class EmployeeExpense {
    private Employee employee;
    private ExpenseType expenseType;
    private String reason;
    private double expenseValue; // Value or quantity, depending on the type of ExpenseType (km,days,cabfare value etc..)

    public EmployeeExpense(Employee employee, ExpenseType expenseType, String reason, double expenseValue) {
        this.employee = employee;
        this.reason = reason;
        this.expenseType = expenseType;
        this.expenseValue = expenseValue;
    }// Constructor.

    public double calculateSeverance(){
        return expenseValue * expenseType.getSeveranceModifier();
    }// Calculates the severance an expense allows for.

    public boolean belongsTo(Employee employee, String code){
        return (this.employee.equals(employee)) && (this.expenseType.getCode().equals(code));
    }// Checks whether an employee is the employee that has this expense.

    public Employee getEmployee() {
        return this.employee;
    }// Returns the employee's name.

    public String toString() {
        return employee.getName() + " " + employee.getLastName() + " spent " + this.calculateSeverance() + " Euro , Reason: " + this.reason;
    }// Prints the employee's full name and the expense value/reason.

    public double getExpenseValue() { return this.expenseValue; }

    public ExpenseType getExpenseType() { return this.expenseType; }

    public String getReason() { return this.reason; }
}