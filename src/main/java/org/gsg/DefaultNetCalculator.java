package org.gsg;

import lombok.RequiredArgsConstructor;
import org.gsg.exception.InvalidTaxRateException;
import org.gsg.exception.TaxRateNotPresentException;

@RequiredArgsConstructor
public class DefaultNetCalculator implements NetCalculator {

    private final TaxRateProvider taxRateProvider;

    /**
     * {@inheritDoc}
     *
     * @throws TaxRateNotPresentException if the tax rate for the specified country is not available.
     */
    @Override
    public Double calculateNetPrice(Double grossPrice, String countryISO) {
        if (taxRateProvider == null) {
            throw new IllegalStateException("Tax rate provider is not initialized.");
        }

        final Double taxRate = taxRateProvider.getTaxRate(countryISO);

        if (taxRate == null || taxRate <= 0.0) {
            throw new InvalidTaxRateException(String.format("Invalid tax rate: %s for country ISO code: %s ", taxRate, countryISO));
        }

        return grossPrice / (1 + taxRate);

    }
}
