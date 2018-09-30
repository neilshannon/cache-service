package com.ntsdev.cacheservice;

import com.ntsdev.cacheservice.autoconfig.CacheServiceAutoConfiguration;
import com.ntsdev.cacheservice.service.CacheService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
public class AutoConfigurationTests {

    private final ApplicationContextRunner runner = new ApplicationContextRunner()
            .withConfiguration(AutoConfigurations.of(CacheServiceAutoConfiguration.class))
            .withPropertyValues("cacheservice.cache.uri=http://localhost");


    @Test
    public void testCacheServiceIsInContext(){
        runner.run(context -> assertThat(context).hasSingleBean(CacheService.class));
    }
}
