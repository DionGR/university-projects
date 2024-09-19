import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/* Test class for TaxEstimator
 * author: Rigatos Dionysios
 * AM: P3200262
*/
public class TestTaxEstimator {
    private TaxEstimator estimator;

    @BeforeEach
    public void setUpTests() {
        estimator = new TaxEstimator();
        estimator.setCalculator(new TaxCalculatorStub(0.1)); // taxFactor = 0.1 = 10%
    }

    /* Case where income is negative */
    @Test
    public void incomeNegative() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            estimator.estimateTax(-1);
        });
    }

    /* Case where income is 0 - Tax should be 0 */
    /* Covered by tax under 1000, however good to show */
    @Test
    public void incomeZero() {
        Assertions.assertEquals(0, estimator.estimateTax(0));
    }

    /* Case where income is (0...10000)  */
    /* Covers if tax under 1000 */
    @Test
    public void taxUnder1000() {
        Assertions.assertEquals(1000, estimator.estimateTax(10000));
    }

    /* Case where income is [10000...30000) */
    /* Covers if tax over 1000 but under 3000 */
    @Test
    public void taxOver1000Under3000() {
        Assertions.assertEquals(2990, estimator.estimateTax(30000));
    }

    /* Case where income is [30000...inf)  */
    /* Covers if tax over 3000 */
    @Test
    public void taxOver3000() {
        Assertions.assertEquals(9000, estimator.estimateTax(100000));
    }
}