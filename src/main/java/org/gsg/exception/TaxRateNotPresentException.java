package org.gsg.exception;

public class TaxRateNotPresentException extends RuntimeException {

    public TaxRateNotPresentException(String message, Throwable cause) {
        super(message, cause);
    }
}
