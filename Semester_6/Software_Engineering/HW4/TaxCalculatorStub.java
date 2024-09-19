/* Stub class for TaxCalculator interface
 * author: Rigatos Dionysios
 * AM: P3200262
 */
public class TaxCalculatorStub implements TaxCalculator{
    private final double taxFactor;

    public TaxCalculatorStub(double taxFactor){
        this.taxFactor = taxFactor;
    }

    @Override
    public int tax(int income) {
        return (int) (income * taxFactor);
    }
}
