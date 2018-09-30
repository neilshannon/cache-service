package com.ntsdev.cacheservice.service;

public interface CacheService {

    /**
     * Invalidates a uri in the cache
     * @param uri the uri to invalidate
     * @return true if the operation was successful
     */
    boolean invalidate(String uri);

}
