/* ExpenseType SUBCLASS
Holds information (unit) regarding an ExpenseTypeB.
 */
public class ExpenseTypeB extends ExpenseType {

    public ExpenseTypeB(String code, String description, double maxMonthlySeverance, double severancePercentage) {
        super(code, description, maxMonthlySeverance, severancePercentage/100);
    }// Returns the severancePercentage divided by 100, so as to ensure that it is of correct value when multiplied (i.e. 0.5, 0.1).
}
