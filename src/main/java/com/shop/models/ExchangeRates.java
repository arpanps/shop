package com.shop.models;

import java.util.Date;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author Arpan Khandelwal
 *
 */
public class ExchangeRates {

    @JsonProperty("base")
    private String baseExchangeRate;

    @JsonProperty("date")
    private Date exchangeDate;

    @JsonProperty("rates")
    private Map<String, Double> exchangeRates;

    public String getBaseExchangeRate() {
        return baseExchangeRate;
    }

    public Date getExchangeDate() {
        return exchangeDate;
    }

    public Map<String, Double> getExchangeRates() {
        return exchangeRates;
    }
}
