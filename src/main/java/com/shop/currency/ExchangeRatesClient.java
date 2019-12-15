package com.shop.currency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.shop.models.ExchangeRates;

/**
 *
 * @author Arpan Khandelwal
 *
 */
@Component
class ExchangeRatesClient {

    private static final String EXCHANGE_RATE_API_URL = "https://api.exchangeratesapi.io/latest?base=%s";

    @Autowired
    private RestTemplate restTemplate;

    ExchangeRates fetchExchangeRates(final String baseExchangeRate) {
        return restTemplate.getForObject(String.format(EXCHANGE_RATE_API_URL, baseExchangeRate), ExchangeRates.class);
    }
}