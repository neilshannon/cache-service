package com.ntsdev.cacheservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * A service to invalidate cache entries by URI in a Varnish cache
 * specified by the cacheservice.cache.uri property or VARNISH_URI
 * set as an environment variable.
 */
@Service
public class VarnishCacheService implements CacheService {

    public static final String X_HTTP_METHOD_OVERRIDE = "X-HTTP-Method-Override";

    @Value("${cacheservice.cache.uri}")
    private String baseUri;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public boolean invalidate(String uri) {
        var headers = new HttpHeaders();
        headers.set(X_HTTP_METHOD_OVERRIDE, "PURGE"); //Varnish uses nonstandard HTTP method

        var request = new HttpEntity<>("", headers); //no body needed

        var result = restTemplate.exchange(baseUri + uri, HttpMethod.POST, request, String.class);

        return result.getStatusCode().is2xxSuccessful();
    }


}
