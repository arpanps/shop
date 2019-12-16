package com.shop.cache;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.UUID;

import org.junit.jupiter.api.Test;

/**
 *
 * @author Arpan Khandelwal
 *
 */
public class LRUCacheTest {

    @Test
    public void testShouldPopulateCache() throws Exception {
        final LRUCache<Integer, UUID> lruCache = generateCacheElements(50, 25);

        assertThat(lruCache).isNotNull();
        assertThat(lruCache).hasSize(25);
    }

    @Test
    public void testCacheShouldNotExceedMaximumSize() throws Exception {
        final LRUCache<Integer, UUID> lruCache = generateCacheElements(50, 150);

        assertThat(lruCache).isNotNull();
        assertThat(lruCache).hasSize(50);
    }

    @Test
    public void testShouldNotRemoveActivelyUsedKey() throws Exception {
        // Generate a Cache with 10 elements.
        final LRUCache<Integer, UUID> lruCache = generateCacheElements(10, 10);

        // Make use of element in position 0, this is now actively used.
        final UUID firstUUID = lruCache.get(0);

        // Add 20 more elements to the cache
        lruCache.putAll(generateCacheElements(20, 20));

        // Ensure cache still contains the element that was previously at position 0.
        assertThat(lruCache.containsValue(firstUUID));
    }

    private LRUCache<Integer, UUID> generateCacheElements(final int maxCacheSize, final int totalElements) {
        final LRUCache<Integer, UUID> lruCache = new LRUCache<>(maxCacheSize);
        for (int i = 0; i < totalElements; i++) {
            lruCache.put(i, UUID.randomUUID());
        }
        return lruCache;
    }
}
