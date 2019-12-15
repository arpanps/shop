package com.shop.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author Arpan Khandelwal
 *
 */
public class Package {

    @JsonProperty
    private final String id;

    @JsonProperty
    private final String name;

    @JsonProperty
    private final String description;

    @JsonProperty("products")
    private final List<Product> productList;

    public Package(final String id, final String name, final String description, final List<Product> productList) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.productList = productList;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public double getPrice() {
        return productList.stream().mapToDouble(Product::getPrice).sum();
    }
}