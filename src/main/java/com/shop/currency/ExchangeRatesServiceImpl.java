package com.shop.currency;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.config.Constants;
import com.shop.execption.ExchangeRatesException;
import com.shop.models.ExchangeRates;

/**
 *
 * @author Arpan Khandelwal
 *
 */
@Service
public class ExchangeRatesServiceImpl implements ExchangeRatesService {

    @Autowired
    private ExchangeRatesClient exchangeRatesClient;

    @Override
    public double exchange(final String requestedCurrency, final double currentPrice) {
        final BigDecimal exchangeRate = fetchExchangeRate(Constants.DEFAULT_CURRENCY, requestedCurrency);
        final BigDecimal newPrice = exchangeRate.multiply(BigDecimal.valueOf(currentPrice));
        return newPrice.setScale(2, BigDecimal.ROUND_CEILING).doubleValue();
    }

    private BigDecimal fetchExchangeRate(final String currentCurrency, final String exchangeCurrency) {
        final ExchangeRates exchangeRates = exchangeRatesClient.fetchExchangeRates(currentCurrency);
        final Double exchangeRate = exchangeRates.getExchangeRates().get(exchangeCurrency.toUpperCase());
        if (exchangeRate == null) {
            throw new ExchangeRatesException(String.format("Could not find exchange rate for %s", exchangeCurrency));
        }
        return BigDecimal.valueOf(exchangeRate);
    }
}
