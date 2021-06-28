/*
SUBCLASS of EmployeeActivity
This is the class used for creating Difference objects.
 */
public class Difference extends EmployeeActivity {
    public Difference(Employee employee, double value) {
        super(employee, value);
    }

    public String toString() {
        return super.toString() + " Difference";
    }// Constructor.
}