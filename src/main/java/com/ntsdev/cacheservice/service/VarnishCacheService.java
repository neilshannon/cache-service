package com.ntsdev.cacheservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * A service to invalidate cache entries by URI in a Varnish cache
 * specified by the cacheservice.cache.uri property or VARNISH_URI
 * set as an environment variable.
 */
@Service
public class VarnishCacheService implements CacheService {

    @Value("${cacheservice.cache.uri}")
    private String baseUri;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public boolean invalidate(String uri) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-HTTP-Method-Override", "PURGE"); //Varnish uses nonstandard HTTP method

        HttpEntity<String> request = new HttpEntity<>("", headers); //no body needed

        ResponseEntity<String> result = restTemplate.exchange(baseUri + uri, HttpMethod.POST, request, String.class);
        return result.getStatusCode().is2xxSuccessful();
    }


}
