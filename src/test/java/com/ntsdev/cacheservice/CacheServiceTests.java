package com.ntsdev.cacheservice;

import com.ntsdev.cacheservice.autoconfig.CacheServiceAutoConfiguration;
import com.ntsdev.cacheservice.service.CacheService;
import com.ntsdev.cacheservice.service.VarnishCacheService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {CacheServiceAutoConfiguration.class})
public class CacheServiceTests {

	@Autowired
	CacheService cacheService;

	@MockBean
	RestTemplate template;

	@Before
	public void configureTemplateMock(){
		when(template.exchange(anyString(), any(), any(), eq(String.class)))
				.thenReturn(new ResponseEntity<>(HttpStatus.OK));
	}

	@Test
	public void contextLoads() {
	}

	@Test
	public void testInvalidate(){
		cacheService.invalidate("/product/1");
		verify(template).exchange(
		        eq("http://localhost/product/1"), eq(HttpMethod.POST), any(), eq(String.class)
        );
	}


}
