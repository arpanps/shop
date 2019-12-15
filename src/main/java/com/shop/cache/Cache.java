package com.shop.cache;

import java.util.Collection;
import java.util.Map;

/**
 *
 * @author Arpan Khandelwal
 *
 */
public interface Cache<K, V> {
    // Query Operations
    /**
     * Returns the value to which the specified key is mapped, or {@code null} if this cache contains no mapping for the
     * key.
     *
     * @param key
     *            the key whose associated value is to be returned
     * @return the value to which the specified key is mapped, or {@code null} if this cache contains no mapping for the
     *         key
     * @throws ClassCastException
     *             if the key is of an inappropriate type for this cache
     *             (<a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>)
     * @throws NullPointerException
     *             if the specified key is null and this cache does not permit null keys
     *             (<a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>)
     */
    V get(Object key);

    // Modification Operations
    /**
     * Associates the specified value with the specified key in this cache (optional operation). If the cache previously
     * contained a mapping for the key, the old value is replaced by the specified value. (A cache <tt>m</tt> is said to
     * contain a mapping for a key <tt>k</tt> if and only if {@link #containsKey(Object) m.containsKey(k)} would return
     * <tt>true</tt>.)
     *
     * @param key
     *            key with which the specified value is to be associated
     * @param value
     *            value to be associated with the specified key
     * @return the previous value associated with <tt>key</tt>, or <tt>null</tt> if there was no mapping for
     *         <tt>key</tt>. (A <tt>null</tt> return can also indicate that the cache previously associated
     *         <tt>null</tt> with <tt>key</tt>, if the implementation supports <tt>null</tt> values.)
     * @throws UnsupportedOperationException
     *             if the <tt>put</tt> operation is not supported by this cache
     * @throws ClassCastException
     *             if the class of the specified key or value prevents it from being stored in this cache
     * @throws NullPointerException
     *             if the specified key or value is null and this cache does not permit null keys or values
     * @throws IllegalArgumentException
     *             if some property of the specified key or value prevents it from being stored in this cache
     */
    V put(K key, V value);

    // Bulk Operations
    /**
     * Copies all of the mappings from the specified cache to this cache (optional operation). The effect of this call
     * is equivalent to that of calling {@link #put(Object,Object) put(k, v)} on this cache once for each mapping from
     * key <tt>k</tt> to value <tt>v</tt> in the specified map. The behavior of this operation is undefined if the
     * specified cache is modified while the operation is in progress.
     *
     * @param m
     *            mappings to be stored in this cache
     * @throws UnsupportedOperationException
     *             if the <tt>putAll</tt> operation is not supported by this cache
     * @throws ClassCastException
     *             if the class of a key or value in the specified map prevents it from being stored in this cache
     * @throws NullPointerException
     *             if the specified cache is null, or if this cache does not permit null keys or values, and the
     *             specified cache contains null keys or values
     * @throws IllegalArgumentException
     *             if some property of a key or value in the specified cache prevents it from being stored in this cache
     */
    void putAll(Map<? extends K, ? extends V> m);

    // Views
    /**
     * Returns a {@link Collection} view of the values contained in this cache.
     *
     * @return a collection view of the values contained in this cache
     */
    Collection<V> values();

}
