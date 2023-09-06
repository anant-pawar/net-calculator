import org.gsg.DefaultNetCalculator;
import org.gsg.FileBasedTaxRateProvider;
import org.gsg.NetCalculator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class NetCalculatorTest {

    private final NetCalculator calculator = new DefaultNetCalculator(
            new FileBasedTaxRateProvider()
    );

    @ParameterizedTest
    @CsvSource({
            "100, DE, 84.03",
            "250, US, 231.48",
            "180.2, CA, 163.82",
            "190.0, UK, 158.33"
    })
    void testCalculateNetPrice(double grossPrice, String countryISO, double expectedNetPrice) {
        Assertions.assertEquals(
                round(calculator.calculateNetPrice(grossPrice, countryISO)),
                expectedNetPrice);
    }

    private double round(double value) {
        return Math.round(value * 100.0) / 100.0;
    }
}
