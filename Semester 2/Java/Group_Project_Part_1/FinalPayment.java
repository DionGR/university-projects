/* EmployActivity
This is the class used for creating FinalPayment objects.
 */
public class FinalPayment extends EmployeeActivity{

    public FinalPayment (Employee employee, double value) {
        super(employee,value);
    }// Constructor.

    public String toString() {
        return super.toString() + " Final Payment";
    }// Returns the final payment.
}
