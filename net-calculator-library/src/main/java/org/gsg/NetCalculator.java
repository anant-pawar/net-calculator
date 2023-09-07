package org.gsg;


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
     * @throws org.gsg.exception.TaxRateNotFoundException If the tax rate for the specified country is not found.
     * @throws org.gsg.exception.InvalidTaxRateException  If the retrieved tax rate is invalid or cannot be converted to a Double.
     * @throws org.gsg.exception.TaxRateLoadingException  If there is an issue loading tax rates from the data source.
     */
    Double calculateNetPrice(Double grossPrice, String countryISO);
}
