import org.gsg.FileBasedTaxRateProvider;
import org.gsg.TaxRateProvider;
import org.gsg.exception.InvalidTaxRateException;
import org.gsg.exception.TaxRateNotPresentException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class TaxRateProviderTest {

    private final TaxRateProvider provider = new FileBasedTaxRateProvider();

    @ParameterizedTest
    @CsvSource({
            "DE, 0.19",
            "US, 0.08",
            "CA, 0.10",
            "UK, 0.20"
    })
    void testGetTaxRate(String countryISO, double expectedTaxRate) {
        Assertions.assertEquals(provider.getTaxRate(countryISO), expectedTaxRate);
    }

    @Test
    void testGetTaxRate_TaxRateNotPresentException() {
        Exception exception = Assertions.assertThrows(
                TaxRateNotPresentException.class, () -> provider.getTaxRate("INVALID"));
        Assertions.assertEquals("Tax rate missing for country code: INVALID", exception.getMessage());
    }

    @Test
    void testGetTaxRate_InvalidTaxRateException() {
        TaxRateProvider taxRateProvider = new FileBasedTaxRateProvider("tax-rates-invalid.properties");
        Exception exception = Assertions.assertThrows(
                InvalidTaxRateException.class, () -> taxRateProvider.getTaxRate("FR"));
        Assertions.assertEquals("Invalid tax rate for country ISO code: FR", exception.getMessage());
    }
}
