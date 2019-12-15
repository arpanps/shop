package com.shop.product;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.shop.config.ServiceConfiguration;
import com.shop.execption.ProductNotFoundException;
import com.shop.models.Product;

/**
 *
 * @author Arpan Khandelwal
 *
 */
@Component
class ProductServiceClient {
    private static final ParameterizedTypeReference<List<Product>> PRODUCT_TYPE_REFERENCE = new ParameterizedTypeReference<List<Product>>() {
    };
    private static final Logger logger = LoggerFactory.getLogger(ProductServiceClient.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ServiceConfiguration serviceConfiguration;

    Product fetchProduct(final String productId) {
        try {
            final HttpHeaders requestHeaders = createRequestHeaders();
            final String productUrl = String.format("%s/%s", serviceConfiguration.getProductServiceUrl(), productId);
            final ResponseEntity<Product> responseEntity = restTemplate.exchange(productUrl, HttpMethod.GET,
                    new HttpEntity<>(requestHeaders), Product.class);
            return responseEntity.getBody();
        } catch (final RestClientException ex) {
            logger.warn("Unable to fetch products [{}]", productId);
            throw new ProductNotFoundException(String.format("Product %s not found", productId));
        }
    }

    List<Product> fetchProducts() {
        try {
            final HttpHeaders requestHeaders = createRequestHeaders();
            final ResponseEntity<List<Product>> responseEntity = restTemplate.exchange(
                    serviceConfiguration.getProductServiceUrl(), HttpMethod.GET, new HttpEntity<>(requestHeaders),
                    PRODUCT_TYPE_REFERENCE);
            return responseEntity.getBody();
        } catch (final RestClientException ex) {
            logger.error("Unable to fetch products", ex);
            return Collections.EMPTY_LIST;
        }
    }

    private HttpHeaders createRequestHeaders() {
        final HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add(HttpHeaders.AUTHORIZATION,
                String.format("Basic %s",
                        Base64.getEncoder()
                                .encodeToString(String
                                        .format("%s:%s", serviceConfiguration.getProductServiceUsername(),
                                                serviceConfiguration.getProductServicePassword())
                                        .getBytes(StandardCharsets.UTF_8))));
        return requestHeaders;
    }
}