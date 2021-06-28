/*
ExpenseType superclass. Holds information regarding an expense's type.
 */
public class ExpenseType {
    private String description;
    private String code;
    private double maxMonthlySeverance;
    private double severanceMultiplier;

    public ExpenseType(String code, String description, double maxMonthlySeverance, double severanceMultiplier) {
        this.code = code;
        this.description = description;
        this.maxMonthlySeverance = maxMonthlySeverance;
        this.severanceMultiplier = severanceMultiplier;
    }// Constructor.

    public String getDescription() {
        return this.description;
    }// Returns the description of this expense type.

    public String getCode() {
        return this.code;
    }// Returns the code of this expense type.

    public double getMaxMonthlySeverance() {
        return this.maxMonthlySeverance;
    }// Returns the max monthly severance of this expense type.

    public double getSeveranceModifier() {
        return this.severanceMultiplier;
    }// Returns the severance modifier of this expense type.
}
