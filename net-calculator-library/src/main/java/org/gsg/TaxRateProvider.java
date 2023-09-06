package org.gsg;

/**
 * This interface defines a contract for classes that provide tax rates based on country ISO codes.
 */
public interface TaxRateProvider {
    /**
     * Gets the tax rate for a specific country based on its ISO code.
     *
     * @param countryISO The ISO code of the country for which to retrieve the tax rate.
     * @return The tax rate for the specified country as a Double value, or null if not found.
     */
    Double getTaxRate(String countryISO);
}
