package org.gsg.exception;

public class TaxRateNotFoundException extends RuntimeException {

    public TaxRateNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
