package com.nsb.practice.cache.guavacache;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

/**
 * 
 * @author Dorae
 *
 */
public class GuavaCacheTest {
    public static void main(String[] args) throws ExecutionException {
        LoadingCache<String, Long> cache = CacheBuilder.newBuilder().maximumSize(1000)
            .expireAfterWrite(10, TimeUnit.MINUTES)
//            .removalListener(null)
            .build(new CacheLoader<String, Long>() {

                @Override
                public Long load(String key) throws Exception {
                    return 1L;
                }
                
            });
        Long value = cache.get("", () -> {
            return -1l;
        });
        System.out.println(value);
    }
}