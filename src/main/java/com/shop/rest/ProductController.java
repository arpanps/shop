package com.shop.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shop.config.Constants;
import com.shop.models.Product;
import com.shop.product.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 *
 * @author Arpan Khandelwal
 *
 */
@RestController
@RequestMapping("api/v1")
@Tag(name = "Product", description = "REST APIs for product")
public class ProductController {

    @Autowired
    private ProductService productService;

    // TODO : Current API is not scalable. Need a criteria based API for pagination.
    @Operation(summary = "Get products", description = "Api to get all products")
    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public ResponseEntity<List<Product>> getProducts(
            @RequestParam(value = "currencyCode", required = false, defaultValue = Constants.DEFAULT_CURRENCY) final String currencyCode) {
        final List<com.shop.models.Product> productList = productService.getProducts(currencyCode);
        if (productList.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(productList);
    }

    @Operation(summary = "Get product", description = "Api to get product by product id")
    @RequestMapping(value = "/products/{productId}", method = RequestMethod.GET)
    public ResponseEntity<Product> getProduct(@PathVariable("productId") final String productId,
            @RequestParam(value = "currencyCode", required = false, defaultValue = Constants.DEFAULT_CURRENCY) final String currencyCode) {
        return ResponseEntity.ok(productService.getProduct(productId, currencyCode));
    }
}
