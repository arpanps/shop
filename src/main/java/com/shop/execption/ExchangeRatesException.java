package com.shop.execption;

/**
 *
 * @author Arpan Khandelwal
 *
 */
public class ExchangeRatesException extends RuntimeException {

    public ExchangeRatesException(final String errorMessage) {
        super(errorMessage);
    }
}
