package com.nsb.practice.cache.guavacache;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

/***
 * 
 * @author Dorae
 *
 */
public class MyGuavaCache {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        LoadingCache<String, String> cache = CacheBuilder.newBuilder()
                .maximumSize(1000).expireAfterWrite(3, TimeUnit.SECONDS)
                .removalListener(e -> {}).build(new CacheLoader<String, String>() {

                    @Override
                    public String load(String key) throws Exception {
                        System.err.println("loading " + key);
                        return "the value of the key: " + key + " is: ns";
                    }
                });
        System.out.println(cache.get("nsb"));
        TimeUnit.SECONDS.sleep(5);
        System.out.println(cache.get("nsb"));
    }
}
