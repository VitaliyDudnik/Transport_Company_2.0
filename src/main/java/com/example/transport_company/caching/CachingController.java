package com.example.transport_company.caching;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/admin")
public class CachingController {

    @Autowired
    private CachingService cachingService;

    @GetMapping("/cache")
    public Object getFromCache(@RequestHeader(name = "X-Token") UUID xToken, String cacheName) {
        return cachingService.getFromCache(cacheName);
    }

    @DeleteMapping("/allCaches")
    public void clearAllCaches(@RequestHeader(name = "X-Token") UUID xToken) {
        cachingService.evictAllCaches();
    }

    @DeleteMapping("/cacheName")
    public void clearCacheName(@RequestHeader(name = "X-Token") UUID xToken, String cacheName) {
        cachingService.evictAllCacheValues(cacheName);
    }
}
