package org.gsg.exception;

public class InvalidTaxRateException extends RuntimeException {
    public InvalidTaxRateException(String message) {
        super(message);
    }
}
