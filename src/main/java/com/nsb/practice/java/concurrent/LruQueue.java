package com.nsb.practice.java.concurrent;

import java.util.LinkedHashMap;
import java.util.Map.Entry;

/**
 * lru队列
 * 
 * @author Dorae
 *
 */
public class LruQueue<K, V> {
    private int cacheSize;
    private LinkedHashMap<K, V> cacheMap;
    public LruQueue(int cacheSize) {
        super();
        this.cacheSize = cacheSize;
        cacheMap = new LinkedHashMap(cacheSize, 0.75f, true) {

            @Override
            protected boolean removeEldestEntry(Entry eldest) {
                if (cacheSize + 1 == cacheMap.size()) {
                    return true;
                } else {
                    return false;
                }
            }
            
        };
    }
    
    public void put(K key, V value) {
        cacheMap.put(key, value);
    }
    
    public V get(K key) {
        return cacheMap.get(key);
    }
    
}
