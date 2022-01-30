/*
This is the Employee class. It holds information regarding the employees.
 */
public class Employee {
    private String name;
    private String lastName;
    private double maxTotalSeverance;

    public Employee(String name, String lastName, double maxTotalSeverance) {
        this.name = name;
        this.lastName = lastName;
        this.maxTotalSeverance = maxTotalSeverance;
    }// Constructor.

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof Employee)) {
            return false;
        }

        Employee c = (Employee) o;
        if (this.getName().equals(c.getName()) && this.getLastName().equals(c.getLastName()) ) {
            return true;
        }
        return false;
    }// Overloaded equals method.

    public String getName() {
        return this.name;
    }// Returns the name of the Employee.

    public String getLastName() {
        return this.lastName;
    }// Returns the surname of the Employee.

    public double getMaxTotalSeverance() {
        return this.maxTotalSeverance;
    }// Returns the surname of the Employee.

    public String toString() {
        return this.getName() + " " + this.getLastName();
    }// Returns the full name of the Employee.
}
