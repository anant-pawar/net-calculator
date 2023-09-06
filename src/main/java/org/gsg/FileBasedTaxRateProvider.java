package org.gsg;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.gsg.exception.TaxRateLoadingException;
import org.gsg.exception.TaxRateNotPresentException;

import java.util.NoSuchElementException;

/**
 * A default implementation of the TaxRateProvider interface that loads tax rates from a properties file.
 */
@Slf4j
public class FileBasedTaxRateProvider implements TaxRateProvider {

    private static final String TAX_RATES_FILE = "tax-rates.properties";
    private final PropertiesConfiguration taxRatesConfiguration;

    public FileBasedTaxRateProvider() {
        this.taxRatesConfiguration = loadTaxRates();
    }

    private PropertiesConfiguration loadTaxRates() {
        final Parameters parameters = new Parameters();
        final FileBasedConfigurationBuilder<PropertiesConfiguration> builder =
                new FileBasedConfigurationBuilder<>(PropertiesConfiguration.class)
                        .configure(parameters.properties()
                                .setFileName(TAX_RATES_FILE));
        try {
            return builder.getConfiguration();
        } catch (ConfigurationException exception) {
            log.error("Failed to load the tax rates from {}.", TAX_RATES_FILE, exception);
            throw new TaxRateLoadingException("Failed to load tax rates from " + TAX_RATES_FILE, exception);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Double getTaxRate(String countryISO) {
        try {
            return taxRatesConfiguration.getDouble(countryISO);
        } catch (NoSuchElementException exception) {
            throw new TaxRateNotPresentException("Tax rate missing for country code: " + countryISO, exception);
        }
    }
}