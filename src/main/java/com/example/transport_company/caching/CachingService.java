package com.example.transport_company.caching;

import com.example.transport_company.exception.ResourceConflictException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CachingService {
    @Autowired
    CacheManager cacheManager;

    public Object getFromCache(String cacheName) {
        log.info("Performing get from cache name '{}'", cacheName);
        if (cacheManager.getCache(cacheName) != null) {
            return cacheManager.getCache(cacheName);
        } else {
            throw new ResourceConflictException("Cache name: '" + cacheName + "' is empty or wrong cache name");
        }
    }

    public void evictAllCacheValues(String cacheName) {
        log.info("Performing evict all cache in: " + cacheName);
        if (cacheManager.getCache(cacheName) != null) {
            cacheManager.getCache(cacheName).clear();
        } else {
            throw new ResourceConflictException("Cache name: '" + cacheName + "' is empty or wrong cache name");
        }
    }

    public void evictAllCaches() {
        log.info("Performing evict all caches: " + cacheManager.getCacheNames());
        cacheManager.getCacheNames()
                .parallelStream()
                .forEach(cacheName -> cacheManager.getCache(cacheName).clear());
    }

    @Scheduled(cron = "0 15 10 ? * SUN")
    public void scheduledEvictAllCaches() {
        log.info("performing the eviction of all caches according to the schedule");
        evictAllCaches();
    }
}
