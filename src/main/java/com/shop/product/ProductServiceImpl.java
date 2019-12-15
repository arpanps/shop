package com.shop.product;

import static java.time.temporal.ChronoUnit.MINUTES;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.cache.Cache;
import com.shop.cache.LRUCache;
import com.shop.config.Constants;
import com.shop.currency.ExchangeRatesService;
import com.shop.models.Product;

/**
 *
 * @author Arpan Khandelwal
 *
 */
@Service
public class ProductServiceImpl implements ProductService {

    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    // TODO This needs to be externalize
    private static final Integer CACHE_REFRESH_TIME_IN_MINUTES = 3;

    @Autowired
    private ExchangeRatesService exchangeRatesService;

    @Autowired
    private ProductServiceClient productServiceClient;

    private Date lastFetchDate = new Date(0);
    private final Cache<String, Product> productCache = new LRUCache<>(100);

    @Override
    public List<Product> getProducts(final String currencyCode) {
        if (shouldRefreshCache()) {
            cacheProducts();
        }
        return new ArrayList<>(productCache.values().stream()
                .peek(product -> product.setPrice(convertPrice(currencyCode, product.getUsdPrice())))
                .collect(Collectors.toList()));
    }

    @Override
    public Product getProduct(final String productId, final String currencyCode) {
        final Product product = getOrFetchProduct(productId);
        product.setPrice(convertPrice(currencyCode, product.getUsdPrice()));
        return product;
    }

    @Override
    public void validate(final List<String> productIds) {
        for (final String productId : productIds) {
            getOrFetchProduct(productId);
        }
    }

    private Product getOrFetchProduct(final String productId) {
        final Product cachedProduct = productCache.get(productId);
        if (cachedProduct == null) {
            logger.debug("Product [{}] did not existing in the cache, fetching and caching it", productId);
            final Product product = productServiceClient.fetchProduct(productId);
            productCache.put(productId, product);
            return product;
        }
        return cachedProduct;
    }

    private void cacheProducts() {
        // Query products service for existing products and cache them.
        final List<Product> existingProducts = productServiceClient.fetchProducts();

        // Update last fetch date.
        lastFetchDate = new Date();

        // Convert products to a map, using their Id as the key.
        final Map<String, Product> productMap = existingProducts.stream()
                .collect(Collectors.toMap(Product::getId, product -> product));

        // Put all the products into the cache.
        productCache.putAll(productMap);
    }

    private double convertPrice(final String currencyCode, final double currentPrice) {
        if (Constants.DEFAULT_CURRENCY.equals(currencyCode)) {
            return currentPrice;
        }
        return exchangeRatesService.exchange(currencyCode, currentPrice);
    }

    private boolean shouldRefreshCache() {
        return MINUTES.between(lastFetchDate.toInstant(), Instant.now()) > CACHE_REFRESH_TIME_IN_MINUTES;
    }
}