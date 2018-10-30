package com.ntsdev.cacheservice;

import com.ntsdev.cacheservice.autoconfig.CacheServiceAutoConfiguration;
import com.ntsdev.cacheservice.service.CacheService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import static com.ntsdev.cacheservice.service.VarnishCacheService.X_HTTP_METHOD_OVERRIDE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
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
        var BASE_URL = "http://varnishhost"; //from src/test/resources/application-test.properties
        var URI_TO_INVALIDATE = "/product/1"; //from src/test/resources/application-test.properties

	    cacheService.invalidate(URI_TO_INVALIDATE);

		var captor = ArgumentCaptor.forClass(HttpEntity.class);

        verify(template).exchange(
                    eq(BASE_URL + URI_TO_INVALIDATE),
                    eq(HttpMethod.POST),
                    captor.capture(),
                    eq(String.class)
                );

		var post = captor.getValue();
		var overrideHeaderValues = post.getHeaders().get(X_HTTP_METHOD_OVERRIDE);
		var overrideMethod = overrideHeaderValues.get(0);

		assertThat(overrideMethod).isEqualTo("PURGE");
	}


}
