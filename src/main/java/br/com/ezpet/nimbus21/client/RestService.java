package br.com.ezpet.nimbus21.client;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestService {

	private final RestTemplate restTemplate;
	
	public RestService(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.basicAuthentication("ezpet399", "393.r34+4ts3").build();
	}
	
	public RestTemplate getObject() {
		return restTemplate;
	}
	
	public RestTemplate getObjectNoCookie() {
		CloseableHttpClient httpClient = HttpClientBuilder.create().disableCookieManagement().build();
		
		this.restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory(httpClient));
		return restTemplate;
	}
}
