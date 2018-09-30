package com.ntsdev.cacheservice.autoconfig;

import com.ntsdev.cacheservice.service.CacheService;
import com.ntsdev.cacheservice.service.VarnishCacheService;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AutoConfigureAfter(SpringDataWebAutoConfiguration.class)
@ConditionalOnProperty("cacheservice.cache.uri")
public class CacheServiceAutoConfiguration {

    @ConditionalOnMissingBean
    @Bean
    public CacheService cacheService(){
        return new VarnishCacheService();
    }

}
