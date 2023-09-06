import org.gsg.DefaultNetCalculator;
import org.gsg.FileBasedTaxRateProvider;
import org.gsg.NetCalculator;
import org.gsg.exception.InvalidTaxRateException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
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
        Assertions.assertEquals(expectedNetPrice, round(calculator.calculateNetPrice(grossPrice, countryISO)));
    }

    @Test
    void testCalculateNetPrice_IllegalStateException() {
        NetCalculator netCalculator = new DefaultNetCalculator(null);
        Exception exception = Assertions.assertThrows(
                IllegalStateException.class, () -> netCalculator.calculateNetPrice(10.0, "DE"));
        Assertions.assertEquals("Tax rate provider is not initialized.", exception.getMessage());
    }

    @Test
    void testCalculateNetPrice_InvalidTaxRateException() {
        NetCalculator netCalculator = new DefaultNetCalculator(
                new FileBasedTaxRateProvider("tax-rates-invalid.properties"));
        Exception exception = Assertions.assertThrows(
                InvalidTaxRateException.class, () -> netCalculator.calculateNetPrice(10.0, "DE"));
        Assertions.assertEquals("Invalid tax rate: 0.0 for country ISO code: DE ", exception.getMessage());
    }

    private double round(double value) {
        return Math.round(value * 100.0) / 100.0;
    }
}
