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
     * @throws org.gsg.exception.TaxRateNotFoundException If the tax rate for the specified country code is not found in the configuration.
     *                                                    The exception may wrap a {@link java.util.NoSuchElementException}.
     * @throws org.gsg.exception.InvalidTaxRateException  If the retrieved tax rate for the specified country code is invalid or cannot be converted to a Double.
     *                                                    The exception may wrap a {@link org.apache.commons.configuration2.ex.ConversionException}.
     */
    Double getTaxRate(String countryISO);
}
