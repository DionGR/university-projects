/* ExpenseType SUBCLASS
Holds information (unit) regarding an ExpenseTypeA.
 */
public class ExpenseTypeA extends ExpenseType {
    private String unit;

    public ExpenseTypeA(String code, String description, double maxMonthlySeverance, double valuePerUnit, String unit) {
        super(code, description, maxMonthlySeverance, valuePerUnit);
        this.unit = unit;
    }// Constructor.
}
