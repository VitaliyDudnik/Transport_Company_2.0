package com.example.transport_company.caching;

import org.springframework.boot.autoconfigure.cache.CacheManagerCustomizer;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.interceptor.SimpleKey;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;

@Component
public class CacheConfiguration implements CacheManagerCustomizer<ConcurrentMapCacheManager>, KeyGenerator {

    @Override
    public void customize(ConcurrentMapCacheManager cacheManager) {
        cacheManager.setCacheNames(Arrays.
                asList("customers",
                        "employees",
                        "order",
                        "allOrders",
                        "user",
                        "allUsers"));
    }
    //todo Consider implementing a key generator
    @Override
    public Object generate(Object target, Method method, Object... params) {
        return generateKey(params);
    }
    public static Object generateKey(Object... params) {
        if (params.length == 0) {
            return SimpleKey.EMPTY;
        }
        if (params.length == 1) {
            Object param = params[0];
            if (param != null && !param.getClass().isArray()) {
                return param;
            }
        }
        return new SimpleKey(params);
    }
}
