/*
This is the Employee Activity superclass. It holds information regarding the employee activites.
 */
public class EmployeeActivity{
        private Employee employee;
        private double value;

        public EmployeeActivity(Employee employee, double value) {
                this.employee = employee;
                this.value = value;
        }// Constructor.

        public boolean belongsTo(Employee employee){
                return this.employee.equals(employee);
        }// Checks whether an employee is the employee that has this activity.

        public double getValue(){
                return this.value;
        }// Returns the value of this activity.

        public String getName() {
                return this.employee.getName();
        }// Returns the name of the employee who conducted this activity.

        public String getLastName() {
                return this.employee.getLastName();
        }// Returns the surname of the employee who conducted this activity.

        public String toString() {
                return (this.getLastName() + " " + this.getName() + ", " + getValue() + " Euro");
        }// Returns the full name of the employee who conducted this activity and the value.
}

