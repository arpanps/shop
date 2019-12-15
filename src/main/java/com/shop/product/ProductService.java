package com.shop.product;

import java.util.List;

import com.shop.models.Product;

/**
 *
 * @author Arpan Khandelwal
 *
 */
public interface ProductService {

    List<Product> getProducts(String currencyCode);

    Product getProduct(String productId, String currencyCode);

    void validate(List<String> productIds);

}