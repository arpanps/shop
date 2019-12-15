package com.shop.cache;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Class to for LRUCache
 *
 * @author Arpan Khandelwal
 */
@SuppressWarnings("serial")
public class LRUCache<K, V> extends LinkedHashMap<K, V> implements Cache<K, V> {
    private final int maxEntries;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;

    private LRUCache(final int initialCapacity, final float loadFactor, final int maxEntries) {
        super(initialCapacity, loadFactor, true);
        this.maxEntries = maxEntries;
    }

    private LRUCache(final int initialCapacity, final int maxEntries) {
        this(initialCapacity, DEFAULT_LOAD_FACTOR, maxEntries);
    }

    public LRUCache(final int maxEntries) {
        this(DEFAULT_INITIAL_CAPACITY, maxEntries);
    }

    @Override
    protected boolean removeEldestEntry(final Map.Entry<K, V> eldest) {
        return size() > maxEntries;
    }
}