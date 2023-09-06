import org.gsg.FileBasedTaxRateProvider;
import org.gsg.TaxRateProvider;
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
        Assertions.assertThrows(TaxRateNotPresentException.class, () -> provider.getTaxRate("INVALID"));
    }
}
