package com.shop.currency;

/**
 *
 * @author Arpan Khandelwal
 *
 */

public interface ExchangeRatesService {
    double exchange(String requestedCurrency, double currentPrice);
}