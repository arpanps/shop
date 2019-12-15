package com.shop.models;

import java.util.List;

/**
 *
 * @author Arpan Khandelwal
 *
 */
public class PackageRequest {

    private String name;
    private String description;
    private List<String> productIds;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getProductIds() {
        return productIds;
    }
}
