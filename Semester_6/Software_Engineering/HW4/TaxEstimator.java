/* TaxEstimator
 * Rigatos Dionysios
 * AM: P3200262
 */

public class TaxEstimator {
	private TaxCalculator calculator;
	
	public void setCalculator(TaxCalculator calculator) {
		this.calculator = calculator;
	}
	
	public int estimateTax(int income) {
		if (income < 0) {
			throw new IllegalArgumentException();
		}
		
		int tax = calculator.tax(income);

		if (tax > 3000) {
			return tax - tax / 10;
		}

		if (tax > 1000) {
			return tax-10;
		}
		
		return tax;
	}
}

