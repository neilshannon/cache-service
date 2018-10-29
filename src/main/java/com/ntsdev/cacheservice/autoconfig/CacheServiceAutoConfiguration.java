package com.ntsdev.cacheservice.autoconfig;

import com.ntsdev.cacheservice.service.CacheService;
import com.ntsdev.cacheservice.service.VarnishCacheService;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * The Cache Service autoconfiguration will create a default VarnishCacheService in the Spring Context
 * if the `cacheservice.cache.uri` property is set and a CacheService bean is not already configured.
 * A defaul† RestTemplate is also created if there is not already an existing RestTemplate in the Spring Context.
 *
 */
@Configuration
@AutoConfigureAfter(SpringDataWebAutoConfiguration.class)
@ConditionalOnProperty("cacheservice.cache.uri")
public class CacheServiceAutoConfiguration {

    @ConditionalOnMissingBean
    @Bean
    public CacheService cacheService(){
        return new VarnishCacheService();
    }

    @ConditionalOnMissingBean
    @Bean
    public RestTemplate defaultRestTemplate(){
        return new RestTemplate();
    }

}
