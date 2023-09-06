package org.gsg;

import org.gsg.exception.TaxRateNotPresentException;

/**
 * An interface for calculating net prices based on gross prices and country ISO codes.
 */
public interface NetCalculator {
    /**
     * Calculates the net price based on the gross price and country ISO code.
     *
     * @param grossPrice The gross price of the product.
     * @param countryISO The ISO code of the country for tax rate calculation.
     * @return The net price after tax deductions.
     * @throws org.gsg.exception.TaxRateNotPresentException if the tax rate for the specified country is not available.
     */
    Double calculateNetPrice(Double grossPrice, String countryISO) throws TaxRateNotPresentException;
}
