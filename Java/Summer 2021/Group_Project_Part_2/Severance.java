/*
SUBCLASS of EmployeeActivity
This is the class used for creating Severance objects.
 */
public class Severance extends EmployeeActivity{
    private ExpenseType expenseType;

    public Severance(Employee employee, ExpenseType expenseType, double value){
        super(employee, value);
        this.expenseType = expenseType;
    }// Constructor.

    public boolean equals (Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof Severance)) {
            return false;
        }

        Severance c = (Severance) o;
        if (this.getValue() == c.getValue() && this.getLastName().equals(c.getLastName()) && this.getCode().equals(c.getCode())) {
            return true;
        }
        return false;
    }// Equals method override.

    public String getCode() {
        return expenseType.getCode();
    }// Returns the code of this expense.

    public String toString() {
        return super.toString() + " " +  expenseType.getDescription() ;
    }// Returns the severance and the description.
}
