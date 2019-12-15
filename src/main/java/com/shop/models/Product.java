package com.shop.models;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author Arpan Khandelwal
 *
 */
public class Product {

    @JsonProperty
    private String id;

    @JsonProperty
    private String name;

    @JsonProperty
    private double price;

    private transient int usdPrice;

    public Product() {
    }

    public Product(final String id, final String name, final int usdPrice) {
        this.id = id;
        this.name = name;
        this.usdPrice = usdPrice;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getUsdPrice() {
        return usdPrice;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(final double price) {
        this.price = price;
    }
}
