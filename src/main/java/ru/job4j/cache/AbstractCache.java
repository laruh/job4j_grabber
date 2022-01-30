package ru.job4j.cache;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public abstract class AbstractCache<K, V> {

    private final Map<K, SoftReference<V>> cache = new HashMap<>();

    public Map<K, SoftReference<V>> getCache() {
        return cache;
    }

    public void put(K key, V value) {
        if (!cache.containsKey(key)) {
            cache.put(key, new SoftReference<>(value));
        }
    }

    public V get(K key) {
        if (!cache.containsKey(key)) {
            throw new NoSuchElementException();
        }
        return cache.get(key).get();
    }

    protected abstract V load(K key);
}
