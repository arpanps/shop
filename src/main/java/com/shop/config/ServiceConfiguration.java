package com.shop.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author Arpan Khandelwal
 *
 */
@Configuration
public class ServiceConfiguration {

    @Value("${product.service.username:user}")
    private String productServiceUsername;

    @Value("${product.service.password:pass}")
    private String productServicePassword;

    @Value("${product.service.url:https://product-service.herokuapp.com/api/v1/products}")
    private String productServiceUrl;

    public String getProductServiceUsername() {
        return productServiceUsername;
    }

    public String getProductServicePassword() {
        return productServicePassword;
    }

    public String getProductServiceUrl() {
        return productServiceUrl;
    }
}